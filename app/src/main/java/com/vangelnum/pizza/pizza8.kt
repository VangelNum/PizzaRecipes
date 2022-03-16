package com.vangelnum.pizza
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class pizza8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var mAdView : AdView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza8)

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
        };
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        };
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