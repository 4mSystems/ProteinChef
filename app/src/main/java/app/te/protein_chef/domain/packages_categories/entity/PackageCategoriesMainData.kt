package app.te.protein_chef.domain.packages_categories.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PackageCategoriesMainData(
  @SerializedName("package")
  val packag: Package,
  val package_types_prices: List<PackageTypesPrice>
)