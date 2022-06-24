package app.te.protein_chef.data.remote

object Keys {
  init {
    System.loadLibrary("native-lib")
  }

  external fun debugBaseUrl(): String
  external fun releaseBaseUrl(): String
  external fun serverClientId(): String
  external fun mapKey(): String
}