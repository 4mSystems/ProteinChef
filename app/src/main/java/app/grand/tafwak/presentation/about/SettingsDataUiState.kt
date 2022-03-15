package app.grand.tafwak.presentation.about

data class SettingsDataUiState(val body: String)

fun SettingsDataUiState.fromSettingsData(body: String): SettingsDataUiState {
  return SettingsDataUiState(
    body = body
  )
}