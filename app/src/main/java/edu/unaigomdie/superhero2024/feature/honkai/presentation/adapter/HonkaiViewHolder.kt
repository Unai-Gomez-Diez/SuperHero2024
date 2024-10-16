package edu.unaigomdie.superhero2024.feature.honkai.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.unaigomdie.superhero2024.app.extension.loadImage
import edu.unaigomdie.superhero2024.databinding.ItemHonkaiBinding
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai

class HonkaiViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val itemHonkai = ItemHonkaiBinding.bind(view)

    fun bind(honkai: Honkai, onClick: (characterId: String) -> Unit) {
        itemHonkai.apply {
            image.loadImage(honkai.img)
            name.text = honkai.name
            path.text = honkai.path
            stars.text = honkai.rarity.toString()
            view.setOnClickListener {
                onClick(honkai.id.toString())
            }

        }
    }
}