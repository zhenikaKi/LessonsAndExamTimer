package ru.kirea.lessonsandexamtimer.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.kirea.lessonsandexamtimer.Consts

/** Общая ViewModel с основной логикой инициализации */
abstract class BaseViewModel<S>: ViewModel() {

    protected val liveData: MutableLiveData<S> = MutableLiveData()
    val coroutineScope: CoroutineScope by lazy {
        CoroutineScope(
            Dispatchers.IO
                    //дочерние корутины выполняются независимо от ошибок в других корутинах
                    + SupervisorJob()
                    //обработка ошибок в корутине
                    + CoroutineExceptionHandler { _, throwable -> handleCoroutineError(throwable) }
        )
    }

    //получить данные, на которые выполнена подписка
    open fun getLiveData(): LiveData<S> = liveData

    override fun onCleared() {
        //завершаем корутины
        coroutineScope.coroutineContext.cancelChildren()
    }

    //обработка ошибок в корутине
    open fun handleCoroutineError(throwable: Throwable) {
        throwable.printStackTrace()
        Log.d(Consts.TAG_LOG, "${throwable.message}")
    }
}