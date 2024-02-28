package com.eymr.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eymr.rickandmorty.databinding.ItemRickMortyBinding
import com.eymr.rickandmorty.domain.models.RickMortyCharacters


class RickMortyAdapter :
    ListAdapter<RickMortyCharacters, RickMortyAdapter.RickMortyViewHolder>(DiffCallbackRickMorty()) {

    inner class RickMortyViewHolder(private val itemBinding: ItemRickMortyBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(characters: RickMortyCharacters) {

            with(itemBinding) {
                tvCharacterName.text = characters.name
                tvCharacterGender.text = characters.gender
                tvCharacterSpecie.text = characters.species
                tvCharacterStatus.text = characters.status
                Glide.with(ivCharacter.context).load(characters.image).into(ivCharacter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickMortyViewHolder {
        return RickMortyViewHolder(
            ItemRickMortyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RickMortyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateData() {
        notifyDataSetChanged()
    }
}

class DiffCallbackRickMorty : DiffUtil.ItemCallback<RickMortyCharacters>() {

    override fun areItemsTheSame(
        oldItem: RickMortyCharacters,
        newItem: RickMortyCharacters
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RickMortyCharacters,
        newItem: RickMortyCharacters
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.id == oldItem.id
    }

}