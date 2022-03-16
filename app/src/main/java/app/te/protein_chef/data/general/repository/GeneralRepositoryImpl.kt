package app.te.protein_chef.data.general.repository

import app.te.protein_chef.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.protein_chef.data.local.preferences.AppPreferences
import app.te.protein_chef.domain.general.repository.GeneralRepository
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val remoteDataSource: GeneralRemoteDataSource,
  private val appPreferences: AppPreferences
) : GeneralRepository