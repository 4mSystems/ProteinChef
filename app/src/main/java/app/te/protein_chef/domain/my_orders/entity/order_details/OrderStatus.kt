package app.te.protein_chef.domain.my_orders.entity.order_details

enum class OrderStatus() {
  pending,
  accepted,
  canceled,
  finished,
  order_in_cancel,
}