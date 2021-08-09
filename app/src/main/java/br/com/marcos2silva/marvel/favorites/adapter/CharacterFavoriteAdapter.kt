package br.com.marcos2silva.marvel.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.marcos2silva.marvel.databinding.AdapterCharacterFavoriteBinding
import br.com.marcos2silva.marvel.model.Character
import br.com.marcos2silva.marvel.model.CharacterComparator
import coil.load

class CharacterFavoriteAdapter :
    ListAdapter<Character, CharacterFavoriteAdapter.CharacterFavoriteViewHolder>(CharacterComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterFavoriteViewHolder =
        CharacterFavoriteViewHolder(
            AdapterCharacterFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CharacterFavoriteViewHolder, position: Int) {
        val item = getItem(position)

        item?.let { holder.bind(it) }
    }

    inner class CharacterFavoriteViewHolder(binding: AdapterCharacterFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val text = binding.textView
        val imageView = binding.imageView

        fun bind(character: Character) {
            text.text = character.name

            imageView.load(character.thumbnail) { crossfade(true) }
        }
    }
}