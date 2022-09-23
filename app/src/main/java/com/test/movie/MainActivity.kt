package com.test.movie

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.test.movie.core.location.LocationService
import com.test.movie.core.location.LocationService.Companion.START
import com.test.movie.core.location.LocationService.Companion.STOP
import com.test.movie.databinding.ActivityMainBinding
import com.test.movie.ui.utils.checkingPermission
import com.test.movie.ui.utils.requestingPermission
import com.test.movie.ui.utils.toMinutes
import com.test.movie.ui.utils.validatePermission

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        startServiceLocation()
        getCurrentLocation()

        bind.apply {

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.moviesFragment,
                    R.id.mapsFragment,
                    R.id.uploadImageFragment,
                ),
                drawerLayout
            )

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController, appBarConfiguration)

            navView.setupWithNavController(navController)

        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        validatePermission {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, toMinutes(4), 0f,{
                    //Se consulta la ubicación para tener una actualización precisa de la misma
                },
                Looper.getMainLooper()
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        stopService(intent.apply { action = STOP })
    }

    private fun startServiceLocation() {
        validatePermission {
            if (checkingPermission()) {
                val intent = Intent(
                    this,
                    LocationService::class.java
                )
                startService(intent.apply { action = START })
            }
        }
    }
}