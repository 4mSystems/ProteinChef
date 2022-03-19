package app.te.protein_chef.domain.packages_categories.entity
import androidx.annotation.Keep

@Keep
data class PackageTypesPrice(
    val days_count: Int,
    val id: Int,
    val price: Double,
    val title: String
)