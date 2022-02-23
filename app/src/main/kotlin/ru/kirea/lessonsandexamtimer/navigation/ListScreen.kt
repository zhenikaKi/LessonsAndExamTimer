package ru.kirea.lessonsandexamtimer.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kirea.lessonsandexamtimer.windows.list.ListFragment
import ru.kirea.lessonsandexamtimer.windows.tag.TagFragment

class ListScreen(): FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = ListFragment()
}