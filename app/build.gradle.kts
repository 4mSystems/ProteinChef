import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.google.protobuf.gradle.*

plugins {
  id(Config.Plugins.androidApplication)
  id(Config.Plugins.kotlinAndroid)
  id(Config.Plugins.kotlinKapt)
  id(Config.Plugins.kotlin_extensions)
  id(Config.Plugins.navigationSafeArgs)
  id(Config.Plugins.hilt)
  id(Config.Plugins.google_services)
  id(Config.Plugins.proto_buf)

}
protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:3.14.0"
  }

  // Generates the java Protobuf-lite code for the Protobufs in this project. See
  // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
  // for more information.
  generateProtoTasks {
    all().forEach { task ->
      task.plugins {
        create("java") {
          option("lite")
        }
      }
    }
  }
}

android {
  compileSdk = Config.AppConfig.compileSdkVersion

  defaultConfig {
    applicationId = Config.AppConfig.appId
    minSdk = Config.AppConfig.minSdkVersion
    targetSdk = Config.AppConfig.compileSdkVersion
    versionCode = Config.AppConfig.versionCode
    versionName = Config.AppConfig.versionName

    vectorDrawables.useSupportLibrary = true
    multiDexEnabled = true
    testInstrumentationRunner = Config.AppConfig.testRunner

  }

  buildTypes {
    getByName("debug") {
      manifestPlaceholders["appName"] = "@string/app_name_debug"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_debug"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_debug_round"
      buildConfigField("String", "API_BASE_URL", Config.Environments.debugBaseUrl)
      buildConfigField("String", "ROOM_DB", Config.Environments.roomDb)
    }

    signingConfigs {
      create("releaseConfig") {
        storeFile = file(rootProject.file("key"))
        storePassword = "123456"
        keyAlias = "My-Key"
        keyPassword = "123456"
      }
    }

    getByName("release") {
      signingConfig = signingConfigs.getByName("releaseConfig")

      isMinifyEnabled = true
      isShrinkResources = true

//      resValue("string", "google_api_key", gradleLocalProperties(rootDir).getProperty("GOOGLE_API_KEY"))
      manifestPlaceholders["appName"] = "@string/app_name"
      manifestPlaceholders["appIcon"] = "@mipmap/ic_launcher_release"
      manifestPlaceholders["appRoundIcon"] = "@mipmap/ic_launcher_release_round"

      buildConfigField("String", "API_BASE_URL", Config.Environments.releaseBaseUrl)
      buildConfigField("String", "ROOM_DB", Config.Environments.roomDb)
    }
  }

  compileOptions {

    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }

  dataBinding {
    isEnabled = true
  }
  lint {
    isCheckReleaseBuilds = false
    // Or, if you prefer, you can continue to check for errors in release builds,
    // but continue the build even when errors are found:
    isAbortOnError = false
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  //Room
  implementation(Libraries.roomVersion)
  kapt(Libraries.roomCompiler)
  implementation(Libraries.roomktx)
  implementation(Libraries.roomCommon)

  // Networking
  implementation(Libraries.retrofit)
  implementation(Libraries.retrofitConverter)
  implementation(Libraries.gson)
  implementation(Libraries.interceptor)
  implementation(Libraries.chuckLogging)

  // Utils
  implementation(Libraries.playServices)
  implementation(Libraries.localization)
  implementation(Libraries.multidex)
  implementation(Libraries.permissions)
  implementation(Libraries.gson)

// paging
  implementation(Libraries.paging_version)
  implementation(Libraries.paging_version_ktx)

  // Hilt
  implementation(Libraries.hilt)
  implementation(Libraries.firebase_platform)
  implementation(Libraries.firebase_messaging)
  kapt(Libraries.hiltDaggerCompiler)
  // Support
  implementation(Libraries.appCompat)
  implementation(Libraries.coreKtx)
  implementation(Libraries.androidSupport)

  // Arch Components
  implementation(Libraries.viewModel)
  implementation(Libraries.lifeData)
  implementation(Libraries.lifecycle)
  implementation(Libraries.viewModelState)

  // Kotlin Coroutines
  implementation(Libraries.coroutinesCore)
  implementation(Libraries.coroutinesAndroid)
//DATA STORE
  implementation(Libraries.datastore_preferences)
  implementation(Libraries.datastore_core)
  implementation(Libraries.datastore_protobuf)

  // UI
  implementation(Libraries.materialDesign)
  implementation(Libraries.navigationFragment)
  implementation(Libraries.navigationUI)
  implementation(Libraries.loadingAnimations)
  implementation(Libraries.alerter)
  implementation(Libraries.coil)
  implementation(Libraries.card_slider)
  implementation(Libraries.powerspinner)
  implementation(Libraries.materialdatetimepicker)

  // Map
  implementation(Libraries.map)
  implementation(Libraries.playServicesLocation)
  implementation(Libraries.rxLocation)
  implementation(Libraries.firebase_messaging)

  //Ted bottom picker
  implementation(Libraries.ted_bottom_picker)

  //Pin code
  implementation(Libraries.pin_code)
  //smarteist
  implementation(Libraries.smartteist)
  //expandable
  implementation(Libraries.expandable)
  //circularprogressbar
  implementation(Libraries.circularprogressbar)

  // Project Modules
  implementation(project(Config.Modules.prettyPopUp))

}
