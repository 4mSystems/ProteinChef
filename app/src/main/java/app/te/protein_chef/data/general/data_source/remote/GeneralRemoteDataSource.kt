package app.te.protein_chef.data.general.data_source.remote

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(private val apiService: GeneralServices) : BaseRemoteDataSource()