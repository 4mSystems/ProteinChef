package app.te.protein_chef.domain.my_locations.entity.request

import androidx.annotation.Keep
import androidx.databinding.ObservableField

@Keep
class AddLocationRequest() {
  var title: String = ""
    set(value) {
      validation.titleError.set(null)
      field = value
    }
  var body: String = ""
  var notes: String = ""
    set(value) {
      validation.noteError.set(null)
      field = value
    }
  var bulding_num: String = ""
    set(value) {
      validation.buildingError.set(null)
      field = value
    }
  var flat_num: String = ""
    set(value) {
      validation.flatError.set(null)
      field = value
    }
  var lat: String = ""

  var lng: String = ""

  @Transient
  var validation: AddLocationValidationException = AddLocationValidationException()

}

@Keep
class AddLocationValidationException {
  @Transient
  var noteError: ObservableField<String> = ObservableField<String>()

  @Transient
  var buildingError: ObservableField<String> = ObservableField<String>()

  @Transient
  var titleError: ObservableField<String> = ObservableField<String>()

  @Transient
  var flatError: ObservableField<String> = ObservableField<String>()


}
