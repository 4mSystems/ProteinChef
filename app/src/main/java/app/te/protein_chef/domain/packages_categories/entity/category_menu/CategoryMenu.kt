package app.te.protein_chef.domain.packages_categories.entity.category_menu
import androidx.annotation.Keep

@Keep
data class CategoryMenu(
    val id: Int,
    val image: String,
    val title: String
)