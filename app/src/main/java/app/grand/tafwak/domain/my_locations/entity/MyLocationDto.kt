package app.grand.tafwak.domain.my_locations.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyLocationDto(
  val id: Int = 0,
  val title: String = "",
  val body: String = "",
  var isDefault: Boolean = false
) : Parcelable
