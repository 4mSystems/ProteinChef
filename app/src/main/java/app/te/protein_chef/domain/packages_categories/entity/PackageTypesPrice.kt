package app.te.protein_chef.domain.packages_categories.entity
import androidx.annotation.Keep

@Keep
data class PackageTypesPrice(
    val days_count: String,
    val id: Int,
    val price: Double,
    val title: String
)