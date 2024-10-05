package edu.unaigomdie.superhero2024.feature.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.app.extension.loadImage
import edu.unaigomdie.superhero2024.feature.domain.SuperHero
import kotlinx.coroutines.runBlocking

class SuperHeroDetailActivity : AppCompatActivity() {

    private lateinit var superHeroesFactory: SuperHeroesFactory
    private lateinit var viewModel: SuperHeroDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_hero_detail)

        superHeroesFactory = SuperHeroesFactory(this)
        viewModel = superHeroesFactory.buildViewModelDetail()

        getSuperHeroId()?.let {
            viewModel.viewCreated(it)
            setupObservers()

        }
    }



    private fun getSuperHeroId(): String? {
        return intent.getStringExtra(KEY_SUPER_HERO_ID)
    }


    private fun setupObservers() {
        viewModel.uiState.observe(this) {
                uistate ->
            uistate.superHero?.let {
                bindData(it)
            }
            uistate.isLoading.let {
                //progressBar.isVisible = it
            }
            uistate.errorApp?.let {
                showError(it)
            }
        }
    }

    private fun bindData(superhero: SuperHero) {
        findViewById<ImageView>(R.id.image).loadImage(superhero.images.sm)
       // Glide.with(this).load(superhero.images.md).into(findViewById(R.id.image))

        findViewById<TextView>(R.id.nombre).text = superhero.name
        findViewById<TextView>(R.id.slug).text = superhero.slug
        findViewById<TextView>(R.id.intelligence).text =
            superhero.powerstats.intelligence.toString()
        findViewById<TextView>(R.id.strength).text = superhero.powerstats.strength.toString()
        findViewById<TextView>(R.id.speed).text = superhero.powerstats.speed.toString()
        findViewById<TextView>(R.id.durability).text = superhero.powerstats.durability.toString()
        findViewById<TextView>(R.id.power).text = superhero.powerstats.power.toString()
        findViewById<TextView>(R.id.combat).text = superhero.powerstats.combat.toString()
        findViewById<TextView>(R.id.occupation).text = superhero.work.occupation
        findViewById<TextView>(R.id.base).text = superhero.work.base
        findViewById<TextView>(R.id.alter_ego).text = superhero.biography.alterEgos
        findViewById<TextView>(R.id.full_name).text = superhero.biography.fullName
        findViewById<TextView>(R.id.place_of_birth).text = superhero.biography.placeOfBirth
    }

    private fun showError(error: ErrorApp) {
        when(error){
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp ->      setError()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknownErrorApp -> TODO()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun setError(){
        setContentView(R.layout.internet_error_activity)
        findViewById<TextView>(R.id.text_view).text = getString(R.string.internet_error_activity_title)
        findViewById<ImageView>(R.id.image_view).setImageResource(R.drawable.ic_internet_error)
    }

    companion object {
        val KEY_SUPER_HERO_ID = "key_super_hero_id"

        fun getIntent(context: Context, superHeroId: String): Intent {

            val intent = Intent(context, SuperHeroDetailActivity::class.java)
            intent.putExtra(KEY_SUPER_HERO_ID, superHeroId)
            return intent
        }
    }
}