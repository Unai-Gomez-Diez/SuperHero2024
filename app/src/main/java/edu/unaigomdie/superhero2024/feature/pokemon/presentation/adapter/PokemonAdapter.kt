package edu.unaigomdie.superhero2024.feature.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Results
import edu.unaigomdie.superhero2024.feature.pokemon.presentation.PokemonDiffUtil

class PokemonAdapter: ListAdapter<Results, PokemonViewHolder>(PokemonDiffUtil()) {
    lateinit var onClick: (pokemonUrl: String) -> Unit
    fun setEvent(onClick: (String) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
    }
}