package app.grand.tafwak.domain.home.models


import androidx.annotation.Keep

@Keep
data class Offers(
  val title: String = "",
  val body: String = "",
  val id: Int = 0,
  var image: String = "",
  val date: String = "",
)