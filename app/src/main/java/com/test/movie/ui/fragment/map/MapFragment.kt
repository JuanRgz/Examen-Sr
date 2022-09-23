package com.test.movie.ui.fragment.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.test.movie.R
import com.test.movie.core.location.FirebaseRepository

class MapFragment : Fragment(), GoogleMap.OnMarkerClickListener{
    var mapReady: Boolean = false
    var Map: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        mapReady = true
        Map = googleMap
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        showAdvice(view)
        getLocations()
    }

    private fun showAdvice(view: View) {
        Snackbar.make(view, R.string.advice, Snackbar.LENGTH_INDEFINITE).setAction("Cerrar"){

        }.show()
    }

    private fun getLocations(){
        FirebaseRepository().getLocation { place ->
            //Agregamos marcadores uno a uno
            Map?.let { map ->
                val location = LatLng(place.latitud, place.longitud)
                map.addMarker(MarkerOptions().position(location).title(place.date))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15F))
            }
        }
    }

    override fun onMarkerClick(place: Marker): Boolean {
        Map?.moveCamera(CameraUpdateFactory.newLatLngZoom(place.position, 15F))
        return true
    }
}