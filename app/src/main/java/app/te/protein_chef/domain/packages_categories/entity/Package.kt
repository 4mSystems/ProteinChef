package app.te.protein_chef.domain.packages_categories.entity
import androidx.annotation.Keep

@Keep
data class Package(
    val id: Int,
    val image: String,
    val title: String
)