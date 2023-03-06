package khvatid.shop.ui.screens

import androidx.lifecycle.ViewModel
import khvatid.shop.ui.components.snackbar.SnackbarManager
import khvatid.shop.ui.components.snackbar.SnackbarMessage.Companion.snackbarMessage
import khvatid.shop.ui.events.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow

abstract class MVIViewModel<T : Event, R : Any> : ViewModel() {


    abstract val uiState: MutableStateFlow<R>

    val showErrorException = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    protected fun onError(error: Throwable) {
        SnackbarManager.showMessage(error.snackbarMessage())
    }

    protected fun onError(error: String) {
        SnackbarManager.showMessage(error)
    }

    /*  */
    abstract fun obtainEvent(event: T)
}