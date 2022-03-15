package app.grand.tafwak.domain.utils

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
  val data: T,
  @SerializedName("msg")
  val message: String,
  val status: Int,
)