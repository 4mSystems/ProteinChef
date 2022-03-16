package app.te.protein_chef.domain.meals.entity
import androidx.annotation.Keep

@Keep
data class MainMealType(
    val id: Int,
    val image: String,
    val selected: Int,
    val title: String,
    val day: String,
    val date: String,
    val week: String,
)