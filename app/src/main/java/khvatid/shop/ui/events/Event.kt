package khvatid.shop.ui.events

sealed interface Event{
    data class ErrorEvent(val throwable: Throwable): Event
}