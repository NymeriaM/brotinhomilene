// java/com/example/teste/SidebarActivity.kt
package com.example.teste

import android.R.layout
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment

class SidebarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_bar)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            // Load default fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, DefaultFragment())
                .commit()
            navigationView.setCheckedItem(R.id.nav_account)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val fragment: Fragment = when (item.itemId) {
            R.id.nav_account -> AccountFragment()
            R.id.nav_favorites -> FavoritesFragment()
            R.id.nav_edit_profile -> EditProfileFragment()
            else -> DefaultFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
