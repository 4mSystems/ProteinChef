package app.grand.tafwak.domain.meals.entity

data class MainMealType(
    val id: Int,
    val image: String,
    val selected: Int,
    val title: String,
    val day: String,
    val date: String,
    val week: String,
)