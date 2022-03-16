package com.vangelnum.pizza

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.ActionBar

class authors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authors)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
        };
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        };

        val image1:ImageView=findViewById(R.id.imageView5) //gastronom
        val image2:ImageView = findViewById(R.id.imageView)// vilkin
        val image3:ImageView = findViewById(R.id.imageView6)// kulinaria
        val image4:ImageView = findViewById(R.id.imageView3)// artlunch
        val image5:ImageView = findViewById(R.id.imageView4)// russianfood

        image1.setOnClickListener {
            val Uri: Uri = Uri.parse("https://www.gastronom.ru/");
            val browser: Intent = Intent(Intent.ACTION_VIEW, Uri);
            startActivity(browser)
        }
        image2.setOnClickListener {
            val Uri: Uri = Uri.parse("https://vilkin.pro/");
            val browser: Intent = Intent(Intent.ACTION_VIEW, Uri);
            startActivity(browser)
        }
        image3.setOnClickListener {
            val Uri: Uri = Uri.parse("https://kulinarnia.ru/");
            val browser: Intent = Intent(Intent.ACTION_VIEW, Uri);
            startActivity(browser)
        }
        image4.setOnClickListener {
            val Uri: Uri = Uri.parse("https://art-lunch.ru/");
            val browser: Intent = Intent(Intent.ACTION_VIEW, Uri);
            startActivity(browser)
        }
        image5.setOnClickListener {
            val Uri: Uri = Uri.parse("https://www.russianfood.com/");
            val browser: Intent = Intent(Intent.ACTION_VIEW, Uri);
            startActivity(browser)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}