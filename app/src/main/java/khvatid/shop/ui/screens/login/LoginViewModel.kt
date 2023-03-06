package khvatid.shop.ui.screens.login

import androidx.core.util.PatternsCompat.*
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.shop.app.UiRoute
import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.usecase.database.LoginUseCase
import khvatid.shop.ui.events.LoginEvents
import khvatid.shop.ui.screens.MVIViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : MVIViewModel<LoginEvents, LoginUiState>() {
    override val uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())

    override fun obtainEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.EmailChange -> {
                reduce(event)
            }

            is LoginEvents.PasswordChange -> {
                reduce(event)
            }

            is LoginEvents.PasswordShowClick -> {
                reduce(event)
            }

            is LoginEvents.LoginButtonClick -> {
                reduce(event)
            }
        }
    }

    private fun reduce(event: LoginEvents.EmailChange) {
        uiState.compareAndSet(
            expect = uiState.value,
            update = uiState.value.copy(
                email = event.value,
                isErrorEmail = !EMAIL_ADDRESS.matcher(event.value).matches()
            )
        )
    }

    private fun reduce(event: LoginEvents.PasswordChange) {
        uiState.compareAndSet(
            expect = uiState.value,
            update = uiState.value.copy(password = event.value)
        )
    }

    private fun reduce(event: LoginEvents.PasswordShowClick) {
        uiState.compareAndSet(
            expect = uiState.value,
            update = uiState.value.copy(showPassword = !uiState.value.showPassword)
        )
    }

    private fun reduce(event: LoginEvents.LoginButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.execute(
                shortUserModel = ShortUserModel(
                    email = uiState.value.email,
                    password = uiState.value.password
                ),
                result = {
                    if (it) {
                        this.launch(Dispatchers.Main) {
                            event.navigate(UiRoute.HOME)
                        }
                        return@execute
                    } else {
                        onError("Account not found")
                        return@execute
                    }
                }
            )
        }
    }

}