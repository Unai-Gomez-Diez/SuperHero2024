package edu.unaigomdie.superhero2024.feature.data.local

import android.content.Context
import android.provider.Settings.Global.getString
import com.google.gson.Gson
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.domain.SuperHero

class SuperHeroXmlLocalDataSource(context: Context) {

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.superhero_name_file_xml), Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveSuperHero(superHero: SuperHero){
        val editor = sharedPref.edit()
        val superHeroGson = gson.toJson(superHero)
        editor.putString("superhero", superHeroGson)
        editor.apply()
    }

    fun getSuperHero(): SuperHero? {
        val superHeroGson = sharedPref.getString("superhero", null)
       return gson.fromJson(superHeroGson, SuperHero::class.java)

    }

    fun saveSuperHeroes(superHeroes: List<SuperHero>){
        val editor = sharedPref.edit()
        val superHeroesGson = gson.toJson(superHeroes)
        editor.putString("superheroes", superHeroesGson)
        editor.apply()
    }
    fun getSuperHeroes(): List<SuperHero>? {
        val superHeroesGson = sharedPref.getString("superheroes", null)
        return gson.fromJson(superHeroesGson, Array<SuperHero>::class.java)?.toList()
    }




}