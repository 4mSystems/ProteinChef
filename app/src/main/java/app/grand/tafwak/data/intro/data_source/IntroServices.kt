package app.grand.tafwak.data.intro.data_source

import app.grand.tafwak.domain.intro.entity.AppTutorial
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.GET

interface IntroServices {

  @GET("V1/app/screens")
  suspend fun intro(): BaseResponse<List<AppTutorial>>

}