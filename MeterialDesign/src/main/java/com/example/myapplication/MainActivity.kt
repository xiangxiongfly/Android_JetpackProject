package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var bottomNavView: BottomNavigationView

    private val fruitList = mutableListOf<Fruit>(
        Fruit("apple", R.drawable.apple),
        Fruit("banana", R.drawable.banana),
        Fruit("cherry", R.drawable.cherry),
        Fruit("watermelon", R.drawable.watermelon),
        Fruit("pear", R.drawable.pear),
        Fruit("strawberry", R.drawable.strawberry),
        Fruit("mango", R.drawable.mango),
        Fruit("orange", R.drawable.orange)
    )

    private val adapter = FruitAdapter(this, fruitList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        bottomNavView = findViewById(R.id.bottomNavView)

        //设置ToolBar
        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_menu)
            setDisplayHomeAsUpEnabled(true)
        }

        //设置DrawerLayout
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        //FloatingActionButton
        fab.setOnClickListener {
            Snackbar.make(it, "确定", Snackbar.LENGTH_SHORT)
                .setAction("撤销") {
                    "撤销".showToast()
                }
                .show()
        }

        //RecyclerView
        repeat(3) {
            fruitList.addAll(fruitList)
        }
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


        //SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            thread {
                Thread.sleep(2000)
                runOnUiThread {
                    fruitList.add(0, Fruit("orange", R.drawable.orange))
                    adapter.notifyDataSetChanged()
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }

        //BottomNavigationView
        bottomNavView.selectedItemId = R.id.home2
        bottomNavView.setOnNavigationItemSelectedListener {
            "点击 ${it.title}".showToast()
            true
        }

//        bottomNavView.setOnNavigationItemReselectedListener {
//            "重复点击 ${it.title}".showToast()
//            true
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.delete -> "删除".showToast()
            R.id.backup -> "备份".showToast()
            R.id.settings -> "设置".showToast()
        }
        return true
    }
}