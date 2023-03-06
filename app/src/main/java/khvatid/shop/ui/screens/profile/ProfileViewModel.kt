package khvatid.shop.ui.screens.profile

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.shop.app.UiRoute
import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.usecase.database.GetShortUserUseCase
import khvatid.shop.domain.usecase.database.GetUserFromDatabaseUseCase
import khvatid.shop.ui.events.ProfileEvents
import khvatid.shop.ui.screens.MVIViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getShortUserUseCase: GetShortUserUseCase,
    private val getUserFromDatabaseUseCase: GetUserFromDatabaseUseCase
) : MVIViewModel<ProfileEvents, ProfileUiState>() {
    override val uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserFromDatabaseUseCase.execute(
                model = getShortUserUseCase.execute(),
                result = {
                    if (it != null) {
                        uiState.compareAndSet(
                            uiState.value,
                            uiState.value.copy(
                                menu = ProfileItemImp.getList(balance = it.balance.toFloat()),
                                firstName = it.firstName,
                                lastName = it.lastName
                            )
                        )
                    }
                }
            )
        }
    }


    override fun obtainEvent(event: ProfileEvents) {
        when (event) {
            is ProfileEvents.MenuClick -> {
                reduce(event)
            }

            is ProfileEvents.ChangePhoto -> {
                reduce(event)
            }
        }
    }

    private fun reduce(event: ProfileEvents.MenuClick) {
        when (event.item) {
            ProfileItemImp.Logout -> {
                event.result(UiRoute.SIGNUP)
            }
        }
    }

    private fun reduce(event: ProfileEvents.ChangePhoto) {
        if (event.uri != null) {
            uiState.compareAndSet(uiState.value, uiState.value.copy(photo = event.uri.toString()))
        } else {
            onError("Cancel")
        }
    }
}