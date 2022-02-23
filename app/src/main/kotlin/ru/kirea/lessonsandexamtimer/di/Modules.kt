package ru.kirea.lessonsandexamtimer.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.kirea.lessonsandexamtimer.MainActivity
import ru.kirea.lessonsandexamtimer.data.repositories.ClassesRepository
import ru.kirea.lessonsandexamtimer.data.repositories.MockClassesRepository
import ru.kirea.lessonsandexamtimer.windows.home.HomeFragment
import ru.kirea.lessonsandexamtimer.windows.home.HomeService
import ru.kirea.lessonsandexamtimer.windows.home.HomeViewModel

object Modules {
    //модуль, содержимое которого должно быть во всем приложении
    val application = module {
        //навигация
        single<Cicerone<Router>>(qualifier = named(Scopes.CICERONE)) {
            Cicerone.create(Router())
        }
        single<NavigatorHolder>(qualifier = named(Scopes.NAVIGATOR_TAB)) {
            get<Cicerone<Router>>(qualifier = named(Scopes.CICERONE)).getNavigatorHolder()
        }
        single<Router>(qualifier = named(Scopes.ROUTER)) {
            get<Cicerone<Router>>(qualifier = named(Scopes.CICERONE)).router
        }

        single<ClassesRepository>(qualifier = named(Scopes.REPOSITORY)) {
            MockClassesRepository()
        }
    }

    //модуль вкладок
    val bottomNavigationWindow = module {
        scope<MainActivity> {
        }
    }

    //модуль основного экрана
    val homeWindow = module {
        scope<HomeFragment> {
            viewModel(qualifier = named(Scopes.HOME_VIEW_MODEL)) {
                HomeViewModel(get(qualifier = named(Scopes.HOME_SERVICE)))
            }

            scoped<HomeService>(qualifier = named(Scopes.HOME_SERVICE)) {
                HomeService(get(qualifier = named(Scopes.REPOSITORY)))
            }
        }
    }
}
