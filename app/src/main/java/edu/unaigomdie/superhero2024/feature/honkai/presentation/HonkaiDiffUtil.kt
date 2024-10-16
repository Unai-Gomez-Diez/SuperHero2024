package edu.unaigomdie.superhero2024.feature.honkai.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai

class HonkaiDiffUtil: DiffUtil.ItemCallback<Honkai>() {
    override fun areItemsTheSame(oldItem: Honkai, newItem: Honkai): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Honkai, newItem: Honkai): Boolean {
        return oldItem == newItem
    }
}


