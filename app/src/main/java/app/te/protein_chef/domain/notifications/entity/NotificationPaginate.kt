package app.te.protein_chef.domain.notifications.entity

import androidx.annotation.Keep
import app.te.protein_chef.domain.general.paginate.Links
import app.te.protein_chef.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class NotificationPaginate(
  @SerializedName("data")
  val notificationData: List<NotificationData>,
  val meta: Meta? = null,
  @SerializedName("links")
  val links: Links? = null
)