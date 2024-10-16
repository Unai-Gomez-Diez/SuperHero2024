package edu.unaigomdie.superhero2024.feature.honkai.data.local

import android.content.Context
import com.google.gson.Gson
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.honkai.domain.Honkai

class HonkaiXmlLocalDataSource(context: Context) {
    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.character_name_file_xml), Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveCharacter(honkai: Honkai) {
        val editor = sharedPref.edit()
        val characterGson = gson.toJson(honkai)
        editor.putString(honkai.id.toString(), characterGson)
        editor.apply()
    }

    fun saveCharacters(honkais: List<Honkai>) {
        val editor = sharedPref.edit()
        honkais.forEach { character ->
            editor.putString(character.id.toString(), gson.toJson(character))
        }
        editor.apply()
    }

    fun getCharacter(characterId: String): Honkai? {
        return sharedPref.getString(characterId, null)?.let {
            gson.fromJson(it, Honkai::class.java)
        }
    }

    fun getCharacters(): List<Honkai> {
        val honkais = mutableListOf<Honkai>()
        val mapCharacters = sharedPref.all as Map<String, String>
        mapCharacters.values.forEach { jsonCharacter ->
            val honkai = gson.fromJson(jsonCharacter, Honkai::class.java)
            honkais.add(honkai)
        }
        return honkais
    }

    fun deleteCharacter(characterId: String) {
        sharedPref.edit().remove(characterId).apply()

    }

    fun deleteCharacters() {
        sharedPref.edit().clear().apply()

    }
}