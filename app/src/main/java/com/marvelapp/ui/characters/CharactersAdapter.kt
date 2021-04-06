package com.marvelapp.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.domain.model.Character
import com.marvelapp.R
import com.marvelapp.databinding.ViewCharacterBinding
import com.marvelapp.ui.common.collectFlow
import com.marvelapp.ui.common.onClickEvents
import com.marvelapp.ui.common.toast
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
            itemView.context.toast(item.name)
        }
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewCharacterBinding.bind(itemView)

        fun bind(item: Character) = with(binding) {
            characterTitle.text = item.name
            Glide
                .with(characterImage.context)
                .load(item.thumbnail?.path)
                .into(characterImage)
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