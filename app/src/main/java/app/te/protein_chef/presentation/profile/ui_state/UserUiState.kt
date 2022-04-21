package app.te.protein_chef.presentation.profile.ui_state

import android.util.Log
import android.view.View
import com.structure.base_mvvm.User

data class UserUiState(var userResponse: User?, var socialToken: String) {
  init {
    Log.e("UserUiState", ": " + socialToken)
  }

  fun showProfileData(): Int =
    if (userResponse?.isCompleted == 1 || socialToken.isNotEmpty()) View.VISIBLE else View.GONE

}