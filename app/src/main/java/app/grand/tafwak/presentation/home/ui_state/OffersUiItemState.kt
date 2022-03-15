package app.grand.tafwak.presentation.home.ui_state

import app.grand.tafwak.domain.home.models.Offers

data class OffersUiItemState(val offers: Offers) {
  fun getOfferId(): Int = offers.id
  fun getOfferTitle(): String = offers.title
  fun getOfferDate(): String = offers.date
  fun getOfferBody(): String = offers.body
  fun getOfferImage(): String = offers.image
}