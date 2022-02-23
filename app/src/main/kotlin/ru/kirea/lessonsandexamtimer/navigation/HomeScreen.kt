package ru.kirea.lessonsandexamtimer.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kirea.lessonsandexamtimer.windows.home.HomeFragment

class HomeScreen(): FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = HomeFragment()
}