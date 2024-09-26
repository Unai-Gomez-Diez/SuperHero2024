package edu.unaigomdie.superhero2024.feature.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.unaigomdie.superhero2024.databinding.ItemSuperheroBinding
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val itemSuperHero = ItemSuperheroBinding.bind(view)

    fun bind(superHero: SuperHero) {
        itemSuperHero.apply {
            Glide.with(view.context).load(superHero.images.sm).into(perfilImage)
            nombre.text = superHero.name
            slug.text = superHero.slug

        }

    }
}