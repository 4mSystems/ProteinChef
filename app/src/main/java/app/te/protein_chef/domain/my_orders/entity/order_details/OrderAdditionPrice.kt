package app.te.protein_chef.domain.my_orders.entity.order_details

data class OrderAdditionPrice(
    val id: Int,
    val meal_type: String,
    val price: Double
)