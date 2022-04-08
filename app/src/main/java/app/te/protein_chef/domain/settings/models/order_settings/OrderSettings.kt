package app.te.protein_chef.domain.settings.models.order_settings

data class OrderSettings(
  var working_hours: String = "",
  var shipp_value: String = "",
  var freeze_days: String = "",
)