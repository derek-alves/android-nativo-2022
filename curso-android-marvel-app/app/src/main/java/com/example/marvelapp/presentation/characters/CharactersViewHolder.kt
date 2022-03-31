package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Character
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemCharactersBinding

class CharactersViewHolder (
    itemCharactersBinding: ItemCharactersBinding
        ):RecyclerView.ViewHolder(itemCharactersBinding.root){
            private val textName = itemCharactersBinding.textName
            private val imageCharacter = itemCharactersBinding.imageCharacter

    fun bind(character:Character){
        textName.text = character.name
        Glide.with(itemView)
            .load(character.imageUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imageCharacter)
    }

    companion object {
        fun create(parent:ViewGroup):CharactersViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharactersBinding.inflate(inflater, parent,false)
            return CharactersViewHolder(itemBinding)
        }
    }
}