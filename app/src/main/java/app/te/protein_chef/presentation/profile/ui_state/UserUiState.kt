package app.te.protein_chef.presentation.profile.ui_state

import android.view.View
import com.structure.base_mvvm.User

data class UserUiState(var userResponse: User?) {
  fun showProfileData(): Int = if (userResponse?.isCompleted == 1) View.VISIBLE else View.GONE

}