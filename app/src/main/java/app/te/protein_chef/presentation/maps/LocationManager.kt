package app.te.protein_chef.presentation.maps

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentSender
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import java.io.IOException
import javax.inject.Inject
class LocationManager @Inject constructor(
  private val fusedLocationClient: FusedLocationProviderClient
) {

  @SuppressLint("MissingPermission")
  fun requestNewLocationData(
    enableLocationUpdates: Boolean,
    onLocationChanged: (location: Location) -> Unit
  ) {
    val mLocationRequest = LocationRequest.create().apply {
      interval = 0
      fastestInterval = 0
      priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val mLocationCallback: LocationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult) {
        val locationList = locationResult.locations
        if (locationList.isNotEmpty()) {
          //The last location in the list is the newest
          val location = locationList.last()
          onLocationChanged(location)
          if (!enableLocationUpdates)
            fusedLocationClient.removeLocationUpdates(this)
        }
      }
    }

    fusedLocationClient.requestLocationUpdates(
      mLocationRequest, mLocationCallback,
      Looper.getMainLooper()
    )
  }


  @SuppressLint("MissingPermission")
  fun getLastKnownLocation(onLocationChanged: (location: Location) -> Unit) {
    fusedLocationClient.lastLocation
      .addOnSuccessListener { location ->
        if (location != null) {
          onLocationChanged(location)
        } else {
          requestNewLocationData(false, onLocationChanged)
        }

      }

  }

  fun getAddress(lat: Double?, lng: Double?, context: Context): String? {
    val geocoder = Geocoder(context)
    return try {
      val addressList =
        geocoder.getFromLocation(lat ?: 0.0, lng ?: 0.0, 1)
      var address = ""
      Log.e("getAddress", "getAddress: "+addressList)
      if (addressList != null && addressList.size > 0) {
        val addressObj = addressList[0]
        address = addressObj.getAddressLine(0)
      }
      address
    } catch (e: IOException) {
      e.printStackTrace()
      null
    }
  }


  fun isLocationEnabled(context: Context): Boolean {
    val locationManager: LocationManager =
      context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
      LocationManager.NETWORK_PROVIDER
    )
  }


  fun openLocationGPS(
    activity: FragmentActivity,
    callBack: (isOpened: Any) -> Unit
  ) {
    val locationRequest = LocationRequest.create().apply {
      priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
    val result = LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build())
    result.addOnCompleteListener {
//      it.showLogMessage("complete")
    }
    result.addOnSuccessListener { locationSettingsResponse ->
//      locationSettingsResponse.showLogMessage("success")
      callBack.invoke(true)
    }

    result.addOnFailureListener { exception ->
//      exception.showLogMessage("exc")
      exception.printStackTrace()
      callBack.invoke(false)
      if (exception is ResolvableApiException) {
        // Location settings are not satisfied, but this can be fixed
        // by showing the user a dialog.
        try {
          // Show the dialog by calling startResolutionForResult(),
          // and check the result in onActivityResult().
          exception.startResolutionForResult(activity, 0x1)
          callBack.invoke(exception)
        } catch (sendEx: IntentSender.SendIntentException) {
          // Ignore the error.
//          sendEx.showLogMessage()
          sendEx.printStackTrace()
        }
      }
    }
  }
}