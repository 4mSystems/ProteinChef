package app.grand.tafwak.domain.home.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeMainData(
  @PrimaryKey(autoGenerate = true)
  val roomId: Int? = null,
  val packages: List<Packages> = listOf(),
  val offers: List<Offers> = ArrayList(),
  val on_zone: Boolean = false,
  val sliders: List<Slider> = listOf()
)