package khvatid.shop.domain.models



data class UserModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val balance: Double
)

data class ShortUserModel(
    val email: String,
    val password: String
)
