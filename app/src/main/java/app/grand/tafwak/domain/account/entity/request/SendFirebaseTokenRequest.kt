package app.grand.tafwak.domain.account.entity.request

data class SendFirebaseTokenRequest(
 var firebase_token: String,
 var platform: String,
 val device_id: String
)