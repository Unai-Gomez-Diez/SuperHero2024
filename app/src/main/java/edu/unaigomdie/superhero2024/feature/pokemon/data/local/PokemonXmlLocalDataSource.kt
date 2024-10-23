package edu.unaigomdie.superhero2024.feature.pokemon.data.local

import android.content.Context
import com.google.gson.Gson
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Page
import edu.unaigomdie.superhero2024.feature.pokemon.domain.Pokemon

class PokemonXmlLocalDataSource(context: Context) {
    val sharedPrefPoke = context.getSharedPreferences(
        context.getString(R.string.pokemon_name_file_xml), Context.MODE_PRIVATE
    )
    val gson = Gson()

    val sharedPrefPage = context.getSharedPreferences(
        context.getString(R.string.page_name_file_xml), Context.MODE_PRIVATE
    )

    fun savePokemon(pokemon: Pokemon) {
        val editor = sharedPrefPoke.edit()
        val pokemonGson = gson.toJson(pokemon)
        editor.putString(pokemon.name, pokemonGson)
        editor.apply()
    }

    fun savePage(page: Page) {
        val editor = sharedPrefPage.edit()
        val pageGson = gson.toJson(page)
        editor.putString("1", pageGson)
        editor.apply()
    }

    fun getPokemon(pokemonName: String): Pokemon? {
        return sharedPrefPage.getString(pokemonName, null)?.let {
            gson.fromJson(it, Pokemon::class.java)
        }
    }

    fun getPage(): Page? {
        return sharedPrefPage.getString("1", null)?.let {
            gson.fromJson(it, Page::class.java)
        }
    }

    fun deletePokemon(pokemonName: String) {
        sharedPrefPoke.edit().remove(pokemonName).apply()
    }

    fun deletePage(count: Int) {
        sharedPrefPage.edit().remove(count.toString()).apply()
    }

    fun saveMorePokemons(page: Page) {
        val editor = sharedPrefPage.edit()
        val pageGson = gson.toJson(page)
        editor.putString("1", pageGson)
        editor.apply()
    }



}