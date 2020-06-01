package com.jp.gym.ui.dashboard.location

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentGymLocationBinding
import com.jp.gym.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class GymLocationFragment : GymAppFragment(), GoogleMap.OnMarkerClickListener {

    private lateinit var mFragmentBinding: FragmentGymLocationBinding
    lateinit var mapFragment: SupportMapFragment

    override fun layoutResource(): Int {
        return R.layout.fragment_gym_location
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentGymLocationBinding
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {



        mapFragment = SupportMapFragment.newInstance()

        //OnMapReady Method Logic

        mapFragment.getMapAsync(OnMapReadyCallback { googlemap ->


            //Declar Type of Map

            googlemap.mapType = GoogleMap.MAP_TYPE_HYBRID
            googlemap.clear()

            googlemap.setTrafficEnabled(true)

            //Static Data For Latitude and Longitude

            val sa = LatLng(23.0661366, 72.5319978)
            val plusfitnees = LatLng(23.0521469, 72.5382373)
            val rsgym = LatLng(23.0464901, 72.5298983)
            val fitnesspoint = LatLng(23.044328, 72.5298017)
            val anytimefitness = LatLng(23.0396581, 72.5293994)
            val sfwgym = LatLng(23.044481, 72.5293699)


            val cameraPosition = CameraPosition.builder()
                .target(sa)
                .zoom(10F)
                .bearing(0F)
                .tilt(45F)
                .build()

            googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))


            //Set Static Markers On GoogleMap

            val markerOptions = MarkerOptions()
            markerOptions.position(plusfitnees)

                .title("Plus Fitness 24/7 Memnagar")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .draggable(true)
                .zIndex(1.0f)
                .flat(true)
                .alpha(0.8F)
                .snippet(plusfitnees.toString())

            //Method For Add Markers To GoogleMap

            googlemap.addMarker(markerOptions)

            markerOptions.position(rsgym)

                .title("RS Gym")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .draggable(true)
                .zIndex(1.0f)
                .flat(true)
                .alpha(0.8F)
                .snippet(rsgym.toString())
            googlemap.addMarker(markerOptions)

            markerOptions.position(fitnesspoint)

                .title("Fitness Point Gym")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .draggable(true)
                .zIndex(1.0f)
                .flat(true)
                .alpha(0.8F)
                .snippet(fitnesspoint.toString())
            googlemap.addMarker(markerOptions)

            markerOptions.position(anytimefitness)

                .title("AnyTime Fitness Gym")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .draggable(true)
                .zIndex(1.0f)
                .flat(true)
                .alpha(0.8F)
                .snippet(anytimefitness.toString())
            googlemap.addMarker(markerOptions)

            markerOptions.position(sfwgym)

                .title("SFW Gym")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .draggable(true)
                .zIndex(1.0f)
                .flat(true)
                .alpha(0.8F)
                .snippet(sfwgym.toString())


            googlemap.addMarker(markerOptions)

            googlemap.getUiSettings().setZoomControlsEnabled(true)
            googlemap.setOnMarkerClickListener(this)


        })

        //For Open Map Fragment in Framelayout

        childFragmentManager.beginTransaction().replace(R.id.mapLayout, mapFragment).commit()
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }
}