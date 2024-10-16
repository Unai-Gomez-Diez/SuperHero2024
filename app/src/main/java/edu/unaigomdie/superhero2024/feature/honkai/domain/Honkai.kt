package edu.unaigomdie.superhero2024.feature.honkai.domain

import kotlinx.serialization.Serializable


@Serializable
data class Honkai (
    val id: Int,
    val name: String,
    val rarity: Int,
    val path: String,
    val element: String,
    val release: String,
    val introduction: String,
    val img: String
)