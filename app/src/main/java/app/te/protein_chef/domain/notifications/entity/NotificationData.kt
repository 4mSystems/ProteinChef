package app.te.protein_chef.domain.notifications.entity

import androidx.annotation.Keep

@Keep

class NotificationData(
  val id: Int = 0,
  val title: String = "",
  val body: String = "",
  val model_id: String = "",
  val model_type: String = "",
  val created_at: String = "",
)