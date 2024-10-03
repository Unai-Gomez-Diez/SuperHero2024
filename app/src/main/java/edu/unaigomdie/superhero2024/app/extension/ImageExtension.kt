package edu.unaigomdie.superhero2024.app.extension


import android.widget.ImageView
import coil.load



fun ImageView.loadImage(url: String){
        this.load(url)
}