package app.te.protein_chef.data.account.data_source.remote

import app.te.protein_chef.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AccountServices {

  @PUT("v1/firebase_token")
  suspend fun sendFirebaseToken(@Body request: SendFirebaseTokenRequest): BaseResponse<*>

  @POST("v1/auth/logout")
  suspend fun logOut(): BaseResponse<*>
}