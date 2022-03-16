package app.te.protein_chef.data.intro.repository

import app.te.protein_chef.data.intro.data_source.IntroRemoteDataSource
import app.te.protein_chef.domain.intro.entity.AppTutorial
import app.te.protein_chef.domain.intro.repository.IntroRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
  private val remoteDataSource: IntroRemoteDataSource
) : IntroRepository {
  override suspend fun intro(): Resource<BaseResponse<List<AppTutorial>>> =
    remoteDataSource.intro()

}