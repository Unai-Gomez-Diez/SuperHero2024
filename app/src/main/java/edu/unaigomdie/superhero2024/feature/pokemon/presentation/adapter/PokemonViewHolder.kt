package edu.unaigomdie.superhero2024.feature.pokemon.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.unaigomdie.superhero2024.databinding.ItemPokemonBinding
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Pokemon
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Results

class PokemonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val itemPokemon = ItemPokemonBinding.bind(view)

    fun bind(result: Results, onClick: (String) -> Unit) {
        itemPokemon.apply {
            name.text = result.name
            view.setOnClickListener {
                onClick(result.url)
            }
        }
    }
}