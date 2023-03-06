package khvatid.shop.app

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import khvatid.shop.ui.components.BottomTab
import khvatid.shop.ui.components.snackbar.SnackbarManager
import khvatid.shop.ui.components.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import khvatid.shop.R


sealed class BottomTabImp(
    override val route: String,
    @StringRes override val label: Int,
    @DrawableRes override val icon: Int
) : BottomTab {

    object Home : BottomTabImp(
        route = UiRoute.HOME,
        label = R.string.home_screen,
        icon = R.drawable.home_ic
    )

    object Likes : BottomTabImp(
        route = UiRoute.EMPTY,
        label = R.string.history_screen,
        icon = R.drawable.heart_ic
    )

    object Order : BottomTabImp(
        route = UiRoute.EMPTY,
        label = R.string.settings_screen,
        icon = R.drawable.order_ic
    )

    object Messages : BottomTabImp(
        route = UiRoute.EMPTY,
        label = R.string.settings_screen,
        icon = R.drawable.message_ic
    )

    object Profile : BottomTabImp(
        route = UiRoute.PROFILE,
        label = R.string.settings_screen,
        icon = R.drawable.profile_ic
    )

    companion object {
        val tabs = listOf(Home, Likes, Order, Messages, Profile)
    }
}


@Stable
class ShopAppState(
    val navController: NavHostController,
    val snackbarHostState: SnackbarHostState,
    val viewModelStoreOwner: ViewModelStoreOwner,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    private val coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch(Dispatchers.IO) {
            snackbarManager.messages.filterNotNull().collect() {
                val text = it.toMessage(resources = resources)
                snackbarHostState.showSnackbar(text)
            }
        }
    }

    // -------------------- Navigation block -------------------------
    val currentRoute: String?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route

    private val route: String?
        get() = navController.currentDestination?.route


    fun navigate(route: String) {
        if (route == UiRoute.SIGNUP || route == UiRoute.HOME) {
            clearAndNavigate(route)
        } else {
            navController.navigate(route)
        }
    }

    fun popBack() {
        navController.popBackStack()
    }

    fun clearAndNavigate(route: String) {
        if (this.route != route) {
            navController.navigate(route) {
                launchSingleTop = true
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }

    // -------------------- Global UI --------------------------------

    val tabs: List<BottomTab> = BottomTabImp.tabs

    fun showTopBar(currentRoute: String?): Boolean {
        return when (currentRoute) {
            UiRoute.HOME -> true
            else -> false
        }
    }

    fun showBottomBar(currentRoute: String?): Boolean {
        return when (currentRoute) {
            BottomTabImp.Home.route -> true
            BottomTabImp.Likes.route -> true
            BottomTabImp.Order.route -> true
            BottomTabImp.Profile.route -> true
            BottomTabImp.Messages.route -> true
            UiRoute.LOGIN -> false
            UiRoute.SIGNUP -> false
            UiRoute.PROFILE -> false
            UiRoute.STUFF_INFO -> false
            null -> {
                true
            }

            else -> {
                throw IllegalArgumentException("Illegal route argument")
            }
        }
    }
}