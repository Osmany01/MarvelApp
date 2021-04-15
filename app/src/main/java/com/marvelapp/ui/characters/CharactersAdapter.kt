package com.marvelapp.ui.characters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.domain.model.characters.Character
import com.marvelapp.R
import com.marvelapp.databinding.ViewCharacterBinding
import com.marvelapp.ui.characterdetails.CharacterDetailsActivity
import com.marvelapp.ui.common.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CharactersAdapter(private val scope: CoroutineScope) :
    ListAdapter<Character, CharactersAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents) {

            itemView.context.startActivity(CharacterDetailsActivity.launchIntent(itemView.context, item.id))
        }
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewCharacterBinding.bind(itemView)

        fun bind(item: Character) = with(binding) {
            characterTitle.text = item.name
            characterImage.loadWithGlide(item.thumbnail?.path?.plus(IMAGE_QUALITY).plus(item.thumbnail?.extension))
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}