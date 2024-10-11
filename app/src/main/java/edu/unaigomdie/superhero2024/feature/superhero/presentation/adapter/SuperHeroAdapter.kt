package edu.unaigomdie.superhero2024.feature.superhero.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.superhero.domain.SuperHero
import edu.unaigomdie.superhero2024.feature.superhero.presentation.SuperHeroDiffUtil

class SuperHeroAdapter : ListAdapter<SuperHero, SuperHeroViewHolder>(SuperHeroDiffUtil()) {
    lateinit var onClick: (superHeroId: String) -> Unit
    fun setEvent(onClick: (String) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_superhero, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
    }
}