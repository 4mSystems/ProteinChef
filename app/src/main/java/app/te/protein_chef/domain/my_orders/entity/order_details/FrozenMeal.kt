package app.te.protein_chef.domain.my_orders.entity.order_details

import androidx.annotation.Keep

@Keep

data class FrozenMeal(
    val date: String,
    val old_date: String,
    val order_id: Int
)