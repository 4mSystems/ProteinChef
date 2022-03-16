package app.te.protein_chef.domain.packages_categories.entity
import androidx.annotation.Keep

@Keep
data class PackageCategoriesMainData(
  val `package`: Package,
  val package_types_prices: List<PackageTypesPrice>
)