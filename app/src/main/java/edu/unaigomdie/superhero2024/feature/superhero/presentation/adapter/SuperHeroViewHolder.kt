package edu.unaigomdie.superhero2024.feature.superhero.presentation.adapter

import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.app.extension.loadImage
import edu.unaigomdie.superhero2024.databinding.ItemSuperheroBinding
import edu.unaigomdie.superhero2024.feature.superhero.domain.SuperHero

class SuperHeroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val itemSuperHero = ItemSuperheroBinding.bind(view)

    fun bind(superHero: SuperHero, onClick: (String) -> Unit) {
        itemSuperHero.apply {
            perfilImage.loadImage(superHero.images.sm)
            nombre.text = superHero.name
            slug.text = superHero.slug
            view.setOnClickListener {
                onClick(superHero.id.toString())
            }
        }

    }
}