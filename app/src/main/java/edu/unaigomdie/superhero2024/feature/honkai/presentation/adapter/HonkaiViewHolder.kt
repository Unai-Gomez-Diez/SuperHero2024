package edu.unaigomdie.superhero2024.feature.honkai.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import edu.unaigomdie.superhero2024.app.extension.loadImage
import edu.unaigomdie.superhero2024.databinding.ItemHonkaiBinding
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character

class HonkaiViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val itemHonkai = ItemHonkaiBinding.bind(view)

    fun bind(character: Character, onClick: (characterId: String) -> Unit) {
        itemHonkai.apply {
            image.loadImage(character.img)
            name.text = character.name
            path.text = character.path
            stars.text = character.rarity.toString()
            view.setOnClickListener {
                onClick(character.id.toString())
            }

        }
    }
}