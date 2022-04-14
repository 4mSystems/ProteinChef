package app.te.protein_chef.domain.notifications.entity

import androidx.annotation.Keep
import app.te.protein_chef.domain.general.paginate.Paginate
import com.google.gson.annotations.SerializedName

@Keep
data class NotificationPaginate(
  @SerializedName("data")
  val notificationData: List<NotificationData>
) : Paginate()