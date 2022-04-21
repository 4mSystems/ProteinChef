package app.te.protein_chef.domain.profile.entity

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.ObservableField
import app.te.protein_chef.domain.utils.BaseRequest
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
class UpdateProfileRequest : BaseRequest(), Parcelable {
  var name: String = ""
    set(value) {
      validation.nameError.set(null)
      field = value
    }

  @Transient
  var isCompleted: Int = 0

  var email: String = ""
    set(value) {
      validation.emailError.set(null)
      field = value
    }
  var phone: String = ""
    set(value) {
      validation.phoneError.set(null)
      field = value
    }

  @Expose
  var gender: String = ""
    set(value) {
      validation.genderError.set(null)
      field = value
    }

  var age: String = ""
    set(value) {
      validation.ageError.set(null)
      field = value
    }
  var weight: String = ""
    set(value) {
      validation.weightError.set(null)
      field = value
    }
  var height: String = ""
    set(value) {
      validation.heightError.set(null)
      field = value
    }
  var socialToken = ""

  @Transient
  var validation: UpdateProfileValidationException = UpdateProfileValidationException()

}


@Keep
class UpdateProfileValidationException {
  @Transient
  var emailError: ObservableField<String> = ObservableField<String>()

  @Transient
  var nameError: ObservableField<String> = ObservableField<String>()

  @Transient
  var phoneError: ObservableField<String> = ObservableField<String>()

  @Transient
  var genderError: ObservableField<String> = ObservableField<String>()

  @Transient
  var ageError: ObservableField<String> = ObservableField<String>()

  @Transient
  var weightError: ObservableField<String> = ObservableField<String>()

  @Transient
  var heightError: ObservableField<String> = ObservableField<String>()

}