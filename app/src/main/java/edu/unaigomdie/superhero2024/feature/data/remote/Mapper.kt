package edu.unaigomdie.superhero2024.feature.data.remote

import edu.unaigomdie.superhero2024.feature.domain.SuperHero

fun SuperHero.toModel(): SuperHero=
    SuperHero(this.id, this.name, this.slug, this.powerStats, this.appearance, this.biography, this.work, this.images)
