package bilinski.arcaneambitions

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView = findViewById(R.id.nav_view)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val controller = navHostFragment.navController
        NavigationUI.setupWithNavController(navView, controller)
    }

    fun setNavigationEnabled(enabled: Boolean) {
        navView.menu.findItem(R.id.navigation_HomeFragment).isEnabled = enabled
        navView.menu.findItem(R.id.navigation_ProfileFragment).isEnabled = enabled
        navView.menu.findItem(R.id.navigation_CreateQuestFragment).isEnabled = enabled
    }
}