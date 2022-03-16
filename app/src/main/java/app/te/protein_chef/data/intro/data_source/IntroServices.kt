package app.te.protein_chef.data.intro.data_source

import app.te.protein_chef.domain.intro.entity.AppTutorial
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.GET

interface IntroServices {

  @GET("V1/app/screens")
  suspend fun intro(): BaseResponse<List<AppTutorial>>

}