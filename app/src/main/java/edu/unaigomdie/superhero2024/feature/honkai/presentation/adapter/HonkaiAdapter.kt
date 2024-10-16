package edu.unaigomdie.superhero2024.feature.honkai.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai
import edu.unaigomdie.superhero2024.feature.honkai.presentation.HonkaiDiffUtil

class HonkaiAdapter: ListAdapter<Honkai, HonkaiViewHolder>(HonkaiDiffUtil()) {
    lateinit var onClick: (characterId: String) -> Unit
    fun setEvent(onClick: (String) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HonkaiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_honkai, parent, false)
        return HonkaiViewHolder(view)
    }

    override fun onBindViewHolder(holder: HonkaiViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
    }
}