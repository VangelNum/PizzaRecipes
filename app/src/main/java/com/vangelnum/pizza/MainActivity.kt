package com.vangelnum.pizza

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView
import com.vangelnum.pizza.R
import com.vangelnum.pizza.authors
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var hasBeenClicked = false
    lateinit var toggle:ActionBarDrawerToggle

    private lateinit var newRecylerview : RecyclerView
    private lateinit var newArrayList : ArrayList<News>
    private lateinit var tempArrayList : ArrayList<News>
    lateinit var imageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var news : Array<String>
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val drawerLayout:DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_message -> {
                    intent = Intent(this@MainActivity,contact::class.java)
                    startActivity(intent)
                }
                R.id.nav_share ->
                {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TITLE, "Спасибо за то, что делишься приложением! ❤")
                        putExtra(Intent.EXTRA_TEXT, "Рецепты пиццы https://play.google.com/store/apps/details?id=com.vangelnum.pizza")
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(sendIntent, "Share images..."))
                }
                R.id.nav_star ->
                {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=com.vangelnum.pizza")
                        )
                    )
                }
                R.id.nav_person->
                {
                    intent = Intent(this@MainActivity, authors::class.java)
                    startActivity(intent)
                }
            }
            true
        }


        imageId = arrayOf(
                R.drawable.pizzaaftertitle1,
                R.drawable.pizzaaftertitle2,
                R.drawable.pizzaaftertitle3,
                R.drawable.pizzaaftertitle4,
                R.drawable.pizzaaftertitle5,
                R.drawable.pizzaaftertitle6,
                R.drawable.pizzaaftertitle7,
                R.drawable.pizzaaftertitle8,
                R.drawable.pizzaaftertitle9,
                R.drawable.pizzaaftertitle10,
                R.drawable.pizzaaftertitle11,
                R.drawable.pizzaaftertitle12,
                R.drawable.pizzaaftertitle13,
                R.drawable.pizzaaftertitle14,
                R.drawable.tost691
        )

        heading = arrayOf(
                getString(R.string.titleresept1),
            getString(R.string.titleresept2),
            getString(R.string.titleresept3),
            getString(R.string.titleresept4),
            getString(R.string.titleresept5),
            getString(R.string.titleresept6),
            getString(R.string.titleresept7),
            getString(R.string.titleresept8),
            getString(R.string.titleresept9),
            getString(R.string.titleresept10),
            getString(R.string.titleresept11),
            getString(R.string.titleresept12),
            getString(R.string.titleresept13),
            getString(R.string.titleresept14),
            getString(R.string.titleresept15)
        )

        news = arrayOf(

                //getString(R.string.news_a),

        )


        newRecylerview =findViewById(R.id.recyclerView2)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)


        newArrayList = arrayListOf<News>()
        tempArrayList = arrayListOf<News>()

        getUserdata()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){

            return true
        }

        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item,menu)

        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView

        searchView.setOnSearchClickListener {
            mAdView.visibility = View.GONE

        }
        searchView.setOnCloseListener {
            mAdView.visibility = View.VISIBLE
            false
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                mAdView.visibility = View.GONE
                tempArrayList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    newArrayList.forEach {
                        if (it.heading.toLowerCase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }
                    newRecylerview.adapter!!.notifyDataSetChanged()
                } else{
                    mAdView.visibility = View.VISIBLE
                    tempArrayList.clear()
                    tempArrayList.addAll(newArrayList)
                    newRecylerview.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getUserdata() {

        for(i in imageId.indices){

            val news = News(imageId[i],heading[i])
            newArrayList.add(news)

        }


        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        tempArrayList.addAll(newArrayList)
        val adapter = MyAdapter(tempArrayList)
        newRecylerview.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                when(position)
                {
                    0-> {
                        intent = Intent(this@MainActivity,pizza1::class.java)
                        startActivity(intent)
                    }
                    1-> {
                        intent = Intent(this@MainActivity,pizza2::class.java)
                        startActivity(intent)
                    }
                    2-> {
                        intent = Intent(this@MainActivity,pizza3::class.java)
                        startActivity(intent)
                    }
                    3-> {
                        intent = Intent(this@MainActivity,pizza4::class.java)
                        startActivity(intent)
                    }
                    4-> {
                        intent = Intent(this@MainActivity,pizza5::class.java)
                        startActivity(intent)
                    }
                    5-> {
                        intent = Intent(this@MainActivity,pizza6::class.java)
                        startActivity(intent)
                    }
                    6-> {
                        intent = Intent(this@MainActivity,pizza7::class.java)
                        startActivity(intent)
                    }
                    7-> {
                        intent = Intent(this@MainActivity,pizza8::class.java)
                        startActivity(intent)
                    }
                    8-> {
                        intent = Intent(this@MainActivity,pizza9::class.java)
                        startActivity(intent)
                    }
                    9-> {
                        intent = Intent(this@MainActivity,pizza10::class.java)
                        startActivity(intent)
                    }
                    10-> {
                        intent = Intent(this@MainActivity,pizza11::class.java)
                        startActivity(intent)
                    }
                    11-> {
                        intent = Intent(this@MainActivity,pizza12::class.java)
                        startActivity(intent)
                    }
                    12-> {
                        intent = Intent(this@MainActivity,pizza13::class.java)
                        startActivity(intent)
                    }
                    13-> {
                        intent = Intent(this@MainActivity,pizza14::class.java)
                        startActivity(intent)
                    }
                    14-> {
                        intent = Intent(this@MainActivity,pizza15::class.java)
                        startActivity(intent)
                    }

                }

            }
        })

    }
}




