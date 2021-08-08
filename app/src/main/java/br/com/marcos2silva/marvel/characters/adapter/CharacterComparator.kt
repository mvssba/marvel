package br.com.marcos2silva.marvel.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.marcos2silva.marvel.data.response.CharacterResponse

object CharacterComparator : DiffUtil.ItemCallback<CharacterResponse>() {
    override fun areItemsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse): Boolean {
        return oldItem == newItem
    }
}