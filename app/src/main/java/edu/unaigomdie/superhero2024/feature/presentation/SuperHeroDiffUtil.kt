package edu.unaigomdie.superhero2024.feature.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroDiffUtil : DiffUtil.ItemCallback<SuperHero>() {
    override fun areItemsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SuperHero, newItem: SuperHero): Boolean {
        return oldItem == newItem
    }
}