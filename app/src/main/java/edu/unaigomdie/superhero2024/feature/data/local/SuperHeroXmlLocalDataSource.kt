package edu.unaigomdie.superhero2024.feature.data.local

import android.content.Context
import com.google.gson.Gson
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroXmlLocalDataSource(context: Context) {

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.superhero_name_file_xml), Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveSuperHero(superHero: SuperHero) {
        val editor = sharedPref.edit()
        val superHeroGson = gson.toJson(superHero)
        editor.putString(superHero.id.toString(), superHeroGson)
        editor.apply()
    }

    fun getById(superHeroId: String): SuperHero? {
        return sharedPref.getString(superHeroId, null)?.let {
            gson.fromJson(it, SuperHero::class.java)
        }
    }

    fun saveSuperHeroes(superHeroes: List<SuperHero>) {
        val editor = sharedPref.edit()
        superHeroes.forEach { superHero ->
            editor.putString(superHero.id.toString(), gson.toJson(superHero))
        }
        editor.apply()
    }

    fun getSuperHeroes(): List<SuperHero> {
        val superHeroes = mutableListOf<SuperHero>()
        val mapSuperHeroes = sharedPref.all as Map<String, String>
        mapSuperHeroes.values.forEach { jsonSuperHero ->
            val superHero = gson.fromJson(jsonSuperHero, SuperHero::class.java)
            superHeroes.add(superHero)
        }
        return superHeroes
    }

    fun deleteSuperHeroes() {
        sharedPref.edit().clear().apply()
    }

    fun deleteSuperHero(superHeroId: String) {
        sharedPref.edit().remove(superHeroId).apply()
    }


}