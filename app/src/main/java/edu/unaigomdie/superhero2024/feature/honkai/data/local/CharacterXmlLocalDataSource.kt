package edu.unaigomdie.superhero2024.feature.honkai.data.local

import android.content.Context
import com.google.gson.Gson
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.feature.honkai.domain.Character

class CharacterXmlLocalDataSource(context: Context) {
    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.character_name_file_xml), Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveCharacter(character: Character) {
        val editor = sharedPref.edit()
        val characterGson = gson.toJson(character)
        editor.putString(character.id.toString(), characterGson)
        editor.apply()
    }

    fun saveCharacters(characters: List<Character>) {
        val editor = sharedPref.edit()
        characters.forEach { character ->
            editor.putString(character.id.toString(), gson.toJson(character))
        }
        editor.apply()
    }

    fun getCharacter(characterId: String): Character? {
        return sharedPref.getString(characterId, null)?.let {
            gson.fromJson(it, Character::class.java)
        }
    }

    fun getCharacters(): List<Character> {
        val characters = mutableListOf<Character>()
        val mapCharacters = sharedPref.all as Map<String, String>
        mapCharacters.values.forEach { jsonCharacter ->
            val character = gson.fromJson(jsonCharacter, Character::class.java)
            characters.add(character)
        }
        return characters
    }

    fun deleteCharacter(characterId: String) {
        sharedPref.edit().remove(characterId).apply()

    }

    fun deleteCharacters() {
        sharedPref.edit().clear().apply()

    }
}