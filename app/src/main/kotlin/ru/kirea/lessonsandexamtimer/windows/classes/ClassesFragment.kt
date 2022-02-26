package ru.kirea.lessonsandexamtimer.windows.classes

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import ru.kirea.lessonsandexamtimer.base.BaseFragment
import ru.kirea.lessonsandexamtimer.data.Classes
import ru.kirea.lessonsandexamtimer.databinding.ClassesFragmentBinding
import ru.kirea.lessonsandexamtimer.di.Scopes
import ru.kirea.lessonsandexamtimer.windows.home.ClassesAdapterListener

class ClassesFragment: BaseFragment<ClassesFragmentBinding>(ClassesFragmentBinding::inflate) {

    private val scope = KoinJavaComponent.getKoin().createScope<ClassesFragment>()
    private val viewModel: ClassesViewModel = scope.get(qualifier = named(Scopes.CLASSES_VIEW_MODEL))

    override fun afterOnCreateView() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
    }

    override fun onResume() {
        super.onResume()
        //получаем список уроков
        viewModel.getClasses()
    }

    /**
     * Обработать событие от viewModel.
     * @param state полученное состояние.
     */
    private fun renderData(state: ClassesState) {
        when(state) {
            //получен список уроков
            is ClassesState.ClassesList -> setClasses(state.classes)
        }
    }

    /** Сформировать список уроков */
    private fun setClasses(classes: List<Classes>) {
        binding?.let {
            it.recyclerview.adapter = ClassesAdapter(classes, object: ClassesAdapterListener {
                override fun buttonZoomClick(zoomUrl: String?) {
                    zoomUrl?.let { url ->
                        val zoom = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(zoom)
                    }
                }
            })
            it.recyclerview.layoutManager = LinearLayoutManager(requireContext())
            it.recyclerview.itemAnimator = DefaultItemAnimator()
        }
    }
}