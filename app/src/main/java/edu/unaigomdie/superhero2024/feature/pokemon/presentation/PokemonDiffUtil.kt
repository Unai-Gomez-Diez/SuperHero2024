package edu.unaigomdie.superhero2024.feature.pokemon.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Results

class PokemonDiffUtil: DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }
}