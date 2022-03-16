package app.te.protein_chef.data.my_coupons.repository

import app.te.protein_chef.data.my_coupons.data_source.MyCouponsDataSource
import app.te.protein_chef.domain.my_coupons.entity.MyCouponsDto
import app.te.protein_chef.domain.my_coupons.repository.MyCouponsRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class MyCouponsRepositoryImpl @Inject constructor(
  private val remoteDataSource: MyCouponsDataSource
) : MyCouponsRepository {

  override suspend fun getMyCoupons(): Resource<BaseResponse<List<MyCouponsDto>>>  = remoteDataSource.myCoupons()

}