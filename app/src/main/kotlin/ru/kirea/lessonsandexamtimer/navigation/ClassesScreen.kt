package ru.kirea.lessonsandexamtimer.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kirea.lessonsandexamtimer.windows.classes.ClassesFragment

class ClassesScreen(): FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = ClassesFragment()
}