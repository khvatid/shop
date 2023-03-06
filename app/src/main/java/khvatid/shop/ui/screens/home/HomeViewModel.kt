package khvatid.shop.ui.screens.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.shop.domain.usecase.network.GetFlashSaleUseCase
import khvatid.shop.domain.usecase.network.GetLatestUseCase
import khvatid.shop.ui.events.HomeEvent
import khvatid.shop.ui.screens.MVIViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestUseCase: GetLatestUseCase,
    private val getFlashSaleUseCase: GetFlashSaleUseCase
) : MVIViewModel<HomeEvent, HomeUiState>() {
    override val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())

    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.CategoryClick -> {}
            is HomeEvent.ItemClick -> {}
            is HomeEvent.WindowLaunch -> reduce(event)
        }
    }

    private fun reduce(event: HomeEvent.WindowLaunch) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val latestRequest = async(Dispatchers.IO) { getLatestUseCase.execute() }
                val flashSaleRequest = async(Dispatchers.IO) { getFlashSaleUseCase.execute() }

                val latest = latestRequest.await()
                val flashSale = flashSaleRequest.await()

                if (latest.isNotEmpty() && flashSale.isNotEmpty()) {
                    uiState.compareAndSet(
                        uiState.value, uiState.value.copy(
                            flashSaleList = flashSale,
                            latestList = latest,
                            showShimmer = false
                        )
                    )
                }
            } catch (e: Exception) {
                onError("Server failure")
            }
        }
    }

    private fun reduce(event: HomeEvent.CategoryClick) {

    }

    private fun reduce(event: HomeEvent.ItemClick) {

    }
}