package br.com.marcos2silva.marvel.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.marcos2silva.marvel.data.response.CharacterResponse
import br.com.marcos2silva.marvel.databinding.AdapterCharacterBinding
import coil.load

class CharacterAdapter(
    differCallback: CharacterComparator,
    private val clickListener: (item: Int) -> Unit
) : PagingDataAdapter<CharacterResponse, CharacterAdapter.CharacterViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            AdapterCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

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

        fun bind(character: CharacterResponse) {
            text.text = character.name

            val url = "${character.thumbnail.path}.${character.thumbnail.extension}"
            imageView.load(url) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_upload)
            }

            container.setOnClickListener { clickListener(character.id) }
        }
    }
}