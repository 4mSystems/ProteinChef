package app.te.protein_chef.domain.my_locations.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import androidx.annotation.Keep

@Keep
@Parcelize
data class MyLocationDto(
  val id: Int = 0,
  val title: String = "",
  val body: String = "",
  var isDefault: Boolean = false
) : Parcelable
