package br.com.marcos2silva.marvel.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.marcos2silva.marvel.databinding.AdapterCharacterBinding
import br.com.marcos2silva.marvel.model.Character
import br.com.marcos2silva.marvel.model.CharacterComparator
import coil.load

class CharacterAdapter(
    private val clickListener: (item: Int) -> Unit,
    private val clickListenerFavorite: (item: Character) -> Unit
) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder = CharacterViewHolder(
        AdapterCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bind(it)
        }
    }

    inner class CharacterViewHolder(binding: AdapterCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        val text = binding.textView
        val imageView = binding.imageView
        val container = binding.root
        val buttonFavorite = binding.imageButtonFavorite

        fun bind(character: Character) {
            text.text = character.name

            imageView.load(character.thumbnail) { crossfade(true) }

            container.setOnClickListener { clickListener(character.id) }

            buttonFavorite.setImageResource(
                if (character.isFavorite) android.R.drawable.star_on else android.R.drawable.star_off
            )
            buttonFavorite.setOnClickListener { clickListenerFavorite(character) }
        }
    }
}