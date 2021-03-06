package app.te.protein_chef.domain.my_orders.entity.order_details

import androidx.annotation.Keep

@Keep

data class MealType(
    val id: Int,
    val image: String,
    val title: String
)