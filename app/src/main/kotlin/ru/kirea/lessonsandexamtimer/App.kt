package ru.kirea.lessonsandexamtimer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.kirea.lessonsandexamtimer.di.Modules

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                Modules.application,
                Modules.bottomNavigationWindow,
                Modules.homeWindow,
                Modules.classesWindow,
            )
        }
    }
}