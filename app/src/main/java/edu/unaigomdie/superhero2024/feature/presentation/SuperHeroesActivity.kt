package edu.unaigomdie.superhero2024.feature.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.SkeletonLayout
import com.faltenreich.skeletonlayout.applySkeleton
import edu.unaigomdie.superhero2024.R
import edu.unaigomdie.superhero2024.app.domain.ErrorApp
import edu.unaigomdie.superhero2024.databinding.FragmentSuperHeroBinding
import edu.unaigomdie.superhero2024.feature.presentation.adapter.SuperHeroAdapter
import kotlinx.coroutines.runBlocking

class SuperHeroesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_superheroes)





    }






}