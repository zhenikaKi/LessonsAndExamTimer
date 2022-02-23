package ru.kirea.lessonsandexamtimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import ru.kirea.lessonsandexamtimer.databinding.MainActivityBinding
import ru.kirea.lessonsandexamtimer.di.Scopes
import ru.kirea.lessonsandexamtimer.navigation.ClassesScreen
import ru.kirea.lessonsandexamtimer.navigation.HomeScreen
import ru.kirea.lessonsandexamtimer.navigation.ListScreen
import ru.kirea.lessonsandexamtimer.navigation.TagScreen

class MainActivity : AppCompatActivity() {

    private val scope = KoinJavaComponent.getKoin().createScope<MainActivity>()
    private var navigatorHolder: NavigatorHolder = scope.get(qualifier = named(Scopes.NAVIGATOR_TAB))
    private val router: Router = scope.get(qualifier = named(Scopes.ROUTER))
    private val navigator = AppNavigator(this, R.id.bottom_container)

    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        router.newRootScreen(HomeScreen())
        setBottomClickListener()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    /** Задать обработчик перехода по табам */
    private fun setBottomClickListener() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //основной экран
                R.id.menu_bottom_home -> {
                    router.newRootScreen(HomeScreen())
                    true
                }

                //уроки
                R.id.menu_bottom_classes -> {
                    router.newRootScreen(ClassesScreen())
                    true
                }

                //Список
                R.id.menu_bottom_list -> {
                    router.newRootScreen(ListScreen())
                    true
                }

                //теги
                R.id.menu_bottom_tag -> {
                    router.newRootScreen(TagScreen())
                    true
                }

                else -> false
            }
        }
    }
}