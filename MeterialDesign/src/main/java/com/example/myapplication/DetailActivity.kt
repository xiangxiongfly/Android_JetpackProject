package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout

class DetailActivity : AppCompatActivity() {
    private lateinit var fruitContent: TextView
    private lateinit var fruitImage: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout

    companion object {
        const val NAME = "name"
        const val IMAGE = "image"

        fun actionStart(context: Context, fruitName: String, fruitImage: Int) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(NAME, fruitName)
                putExtra(IMAGE, fruitImage)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra(NAME) ?: ""
        val image = intent.getIntExtra(IMAGE, 0)

        fruitContent = findViewById(R.id.fruitContent)
        fruitImage = findViewById(R.id.fruitImage)
        toolbar = findViewById(R.id.toolbar)
        collapsingToolbar = findViewById(R.id.collapsingToolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collapsingToolbar.title = name
//        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#000000"))
//        collapsingToolbar.setCollapsedTitleTextColor(Color.parseColor("#ffffff"))

        fruitImage.setImageResource(image)
        fruitContent.text = getRandomContent(name)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getRandomContent(name: String) = name.repeat(500)

}