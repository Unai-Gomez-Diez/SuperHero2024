package edu.unaigomdie.superhero2024.feature.pokemon.domain

data class Page (
    val count: Int,
    val next: String,
    val previous: Boolean,
    val results: List<Results>
)

data class Results(
    val name: String,
    val url: String
)

data class Pokemon(
    val id: Int,
    val name: String
)