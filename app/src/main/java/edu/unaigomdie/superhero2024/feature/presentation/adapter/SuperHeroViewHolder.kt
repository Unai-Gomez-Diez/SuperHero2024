package edu.unaigomdie.superhero2024.feature.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.unaigomdie.superhero2024.databinding.ItemSuperheroBinding
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val itemSuperHero = ItemSuperheroBinding.bind(view)

    fun bind(superHero: SuperHero) {
        itemSuperHero.apply {
            nombre.text = superHero.name
            slug.text = superHero.id.toString()

        }

    }
}