package ru.appricot.medreg.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class FragmentUtil {
    companion object {
        fun replace(supportFragmentManager: FragmentManager, layout: Int, fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(layout, fragment)
                .commit()

        }

        fun replaceWithBackStack(supportFragmentManager: FragmentManager, layout: Int, fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(layout, fragment)
                .commit()

        }
    }
}