package khvatid.shop.ui.events

sealed class LoginEvents : Event {
    data class EmailChange(val value: String) : LoginEvents()
    data class PasswordChange(val value: String) : LoginEvents()
    object PasswordShowClick : LoginEvents()
    data class LoginButtonClick(val navigate: (String) -> Unit) : LoginEvents()
}
