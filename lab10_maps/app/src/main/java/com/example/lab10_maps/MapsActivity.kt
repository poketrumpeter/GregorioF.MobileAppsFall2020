package com.example.lab10_maps

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.IntentSender
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    //Permission constants
    private val REQUEST_LOCATION_PERMISSIONS = 1
    private val REQUEST_CHECK_SETTINGS = 2

    //Getting User Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    //location Request
    private lateinit var locationRequest: LocationRequest

    //When Client has a new location
    private lateinit var locationCallback:LocationCallback

    private var currentLocation : Location? = null
    private var marker:Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        enableMyLocation()

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
//
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    //LOCATION REQUEST
    private fun createLocationRequest(){
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 500
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val settingsClient = LocationServices.getSettingsClient(this)
        val task = settingsClient.checkLocationSettings((builder.build()))

        task.addOnSuccessListener {
            startLocationUpdates()
        }

        task.addOnFailureListener{ e ->
            if(e is ResolvableApiException){
                try{
                    e.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                }
                catch (sendEx: IntentSender.SendIntentException){

                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(){
        if(mMap.isMyLocationEnabled){
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            locationCallback = object: LocationCallback(){
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    onLocationChanged(p0.lastLocation)

                }
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }
    }

    private fun onLocationChanged(location:Location){
        if(location != null){
            currentLocation = location

            val currentLatLng = LatLng(location.latitude, location.longitude)

            if(marker == null){
                var markerOptions = MarkerOptions()

                markerOptions.position(currentLatLng)

                markerOptions.title("You are here")

                marker = mMap.addMarker(markerOptions)
            }
            else{
                marker!!.position = currentLatLng
            }

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,15f))
        }
    }

    //PERMISSIONS

    private fun enableMyLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
            createLocationRequest()
        }
        else{
            //Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                val alertDialog = AlertDialog.Builder(this)

                alertDialog.setTitle(R.string.dialog_title).setMessage(R.string.dialog_message)
                alertDialog.apply {
                    //Positive OK Button
                    setPositiveButton(R.string.dialogOk, DialogInterface.OnClickListener { dialog, which ->

                    ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_LOCATION_PERMISSIONS)
                    })

                    //Negative Cancel Button
                    setNegativeButton(R.string.dialogCancel, DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })

                }
                alertDialog.create().show()
            }

            else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSIONS)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray){
        when(requestCode){
            REQUEST_LOCATION_PERMISSIONS -> {
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    //permission is granted
                    enableMyLocation()
                }
                else{
                    Snackbar.make(findViewById(R.id.map), "Location Access Denied", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }



}