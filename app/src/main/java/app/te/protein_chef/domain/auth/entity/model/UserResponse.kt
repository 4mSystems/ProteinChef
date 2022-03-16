package app.te.protein_chef.domain.auth.entity.model

import androidx.annotation.Keep

@Keep
data class UserResponse(
  val email: String = "",
  val id: Int = 0,
  val image: String = "",
  val is_completed: Int = 0,
  val name: String = "",
  val phone: String = "",
  val gender: String = "",
  val age: Int = 0,
  val weight: Float = 0F,
  val height: Float = 0F,
  val provider: String = "",
  val social_id: String = "",
  val token: String = ""
)