package br.com.marcos2silva.marvel.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import br.com.marcos2silva.marvel.characters.presentation.CharactersFragment
import br.com.marcos2silva.marvel.favorites.CharactersFavoritesFragment

class PagerAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CharactersFragment()
            else -> CharactersFavoritesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Todos Heróis"
            else -> "Heróis favoritos"
        }
    }
}