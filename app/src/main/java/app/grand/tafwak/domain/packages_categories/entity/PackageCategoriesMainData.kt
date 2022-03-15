package app.grand.tafwak.domain.packages_categories.entity

data class PackageCategoriesMainData(
  val `package`: Package,
  val package_types_prices: List<PackageTypesPrice>
)