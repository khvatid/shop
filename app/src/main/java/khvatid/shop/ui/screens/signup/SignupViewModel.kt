package khvatid.shop.ui.screens.signup

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.shop.app.UiRoute
import khvatid.shop.domain.models.UserModel
import khvatid.shop.domain.usecase.database.SignupUserUseCase
import khvatid.shop.ui.events.SignupEvents
import khvatid.shop.ui.screens.MVIViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUserUseCase: SignupUserUseCase,
) : MVIViewModel<SignupEvents, SignupScreenState>() {


    override val uiState: MutableStateFlow<SignupScreenState> =
        MutableStateFlow(SignupScreenState())

    override fun obtainEvent(event: SignupEvents) {
        when (event) {
            is SignupEvents.FirstNameChange -> {
                reduce(event)
            }

            is SignupEvents.LastNameChange -> {
                reduce(event)
            }

            is SignupEvents.EmailChange -> {
                reduce(event)
            }

            is SignupEvents.SignupButtonClick -> {
                reduce(event)
            }

            is SignupEvents.LoginButtonClick -> {
                reduce(event)
            }

            is SignupEvents.GoogleButtonClick -> {
                reduce(event)
            }

            else -> {}
        }
    }


    private fun reduce(event: SignupEvents.EmailChange) {
        uiState.compareAndSet(
            expect = uiState.value,
            update = uiState.value.copy(
                email = event.value,
                isErrorEmail = !EMAIL_ADDRESS.matcher(event.value).matches()
            )
        )
    }

    private fun reduce(event: SignupEvents.FirstNameChange) {
        uiState.compareAndSet(
            expect = uiState.value,
            update = uiState.value.copy(
                firstName = event.value
            )
        )
    }

    private fun reduce(event: SignupEvents.LastNameChange) {
        uiState.compareAndSet(
            expect = uiState.value,
            update = uiState.value.copy(
                lastName = event.value
            )
        )
    }


    private fun reduce(event: SignupEvents.SignupButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {
            val password = "qwerty123"
            signupUserUseCase.execute(
                userModel = UserModel(
                    firstName = uiState.value.firstName,
                    lastName = uiState.value.lastName,
                    email = uiState.value.email,
                    password = password,
                    balance = Random.nextDouble(100.00, 5000.00)
                ),
                onError = {
                    if (it != null) {
                        onError("Account already exists")
                    } else {
                        this.launch(Dispatchers.Main) {
                            event.navigate(UiRoute.HOME)
                        }
                    }
                }
            )
        }
    }


    private fun reduce(event: SignupEvents.LoginButtonClick) {
        viewModelScope.launch(Dispatchers.Main) {
            event.navigate(UiRoute.LOGIN)
        }
    }


    private fun reduce(event: SignupEvents.GoogleButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {
            val password = "qwerty123"
            signupUserUseCase.execute(
                userModel = UserModel(
                    firstName = "Name",
                    lastName = "Surname",
                    email = "google@gmail.com",
                    password = password,
                    balance = 0.0
                ),
                onError = {
                    if (it != null) {
                        onError("Account already exists")
                    } else {
                        this.launch(Dispatchers.Main) {
                            event.navigate(UiRoute.HOME)
                        }
                    }
                }
            )
        }
    }


}