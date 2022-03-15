package app.grand.tafwak.data.my_coupons.repository

import app.grand.tafwak.data.my_coupons.data_source.MyCouponsDataSource
import app.grand.tafwak.domain.my_coupons.entity.MyCouponsDto
import app.grand.tafwak.domain.my_coupons.repository.MyCouponsRepository
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource
import javax.inject.Inject

class MyCouponsRepositoryImpl @Inject constructor(
  private val remoteDataSource: MyCouponsDataSource
) : MyCouponsRepository {

  override suspend fun getMyCoupons(): Resource<BaseResponse<List<MyCouponsDto>>>  = remoteDataSource.myCoupons()

}