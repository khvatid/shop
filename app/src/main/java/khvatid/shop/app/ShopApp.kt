package khvatid.shop.app

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import khvatid.shop.ui.components.ShopBottomBar
import khvatid.shop.ui.components.ShopTopBar
import khvatid.shop.ui.components.snackbar.SnackbarManager
import khvatid.shop.ui.screens.home.HomeScreen
import khvatid.shop.ui.screens.login.LoginScreen
import khvatid.shop.ui.screens.profile.ProfileScreen
import khvatid.shop.ui.screens.signup.SignupScreen
import khvatid.shop.ui.screens.stuff.StuffScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopApp() {
    val state = rememberAppState()
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = {
            SnackbarHost(hostState = state.snackbarHostState, snackbar = { it ->
                Snackbar(
                    snackbarData = it,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            })
        },
        bottomBar = {
            if (state.showBottomBar(state.currentRoute)) {
                ShopBottomBar(
                    bottomTabs = state.tabs,
                    currentRoute = state.currentRoute,
                    onClick = state::clearAndNavigate
                )
            }
        },
        topBar = {
            if (state.showTopBar(state.currentRoute)){
                ShopTopBar()
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            navController = state.navController, startDestination = "main"
        ) {
            shopNavGraph(state = state)
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = LocalContext.current.resources,
    snackbarState: SnackbarHostState = remember {
        SnackbarHostState()
    },
    viewModelStoreOwner: ViewModelStoreOwner? = LocalViewModelStoreOwner.current,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember {
    ShopAppState(
        navController = navController,
        viewModelStoreOwner = viewModelStoreOwner!!,
        snackbarManager = snackbarManager,
        resources = resources,
        snackbarHostState = snackbarState,
        coroutineScope = coroutineScope
    )
}


object UiRoute {
    const val LOGIN: String = "login"
    const val SIGNUP: String = "signup"
    const val HOME: String = "home"
    const val PROFILE: String = "profile"
    const val STUFF_INFO: String = "stuff"
    const val EMPTY: String = "empty"

    const val STUFF_ID: String = "stuff_id"
    const val STUFF_ID_ARG: String = "?$STUFF_ID={$STUFF_ID}"
}

fun NavGraphBuilder.shopNavGraph(state: ShopAppState) {
    navigation(
        startDestination = UiRoute.SIGNUP,
        builder = { mainGraph(state = state) },
        route = "main"
    )
}

fun NavGraphBuilder.mainGraph(state: ShopAppState) {
    composable(route = UiRoute.HOME) {
        HomeScreen()
    }
    composable(route = UiRoute.PROFILE) {
        ProfileScreen(navigate = state::navigate)
    }
    composable(route = UiRoute.SIGNUP) {
        SignupScreen(navigate = state::navigate)
    }
    composable(route = UiRoute.LOGIN) {
        LoginScreen(navigate = state::navigate)
    }
    composable(route = UiRoute.EMPTY) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Not yet implemented")
        }
    }
    composable(
        route = "${UiRoute.STUFF_INFO}/${UiRoute.STUFF_ID_ARG}",
        arguments = listOf(navArgument(UiRoute.STUFF_ID) {
            type = NavType.IntType
        })
    ) {
        StuffScreen(id = it.arguments!!.getInt(UiRoute.STUFF_ID))
    }
}
