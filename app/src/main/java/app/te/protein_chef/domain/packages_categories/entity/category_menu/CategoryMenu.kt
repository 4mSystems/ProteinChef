package app.te.protein_chef.domain.packages_categories.entity.category_menu
import androidx.annotation.Keep

@Keep
data class CategoryMenu(
    val id: Int,
    val meal_type_id: Int,
    val image: String,
    val price: Double,
    val title: String
)