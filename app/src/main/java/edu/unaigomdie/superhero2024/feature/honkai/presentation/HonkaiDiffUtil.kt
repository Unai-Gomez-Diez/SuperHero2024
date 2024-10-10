package edu.unaigomdie.superhero2024.feature.honkai.presentation

import androidx.recyclerview.widget.DiffUtil
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character

class HonkaiDiffUtil: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}


