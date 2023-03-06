package khvatid.shop.ui.events

sealed class SignupEvents : Event {
    data class FirstNameChange(val value: String) : SignupEvents()
    data class LastNameChange(val value: String) : SignupEvents()
    data class EmailChange(val value: String) : SignupEvents()
    data class SignupButtonClick(val navigate: (String) -> Unit) : SignupEvents()
    data class LoginButtonClick(val navigate: (String) -> Unit) : SignupEvents()
    data class GoogleButtonClick(val navigate: (String)->Unit) : SignupEvents()
}
