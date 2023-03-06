package khvatid.shop.ui.screens.profile

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import khvatid.shop.R
import khvatid.shop.ui.components.ProfileItem
import khvatid.shop.ui.components.ShopButton
import khvatid.shop.ui.components.ShopIconButton
import khvatid.shop.ui.events.ProfileEvents
import khvatid.shop.ui.events.ProfileEvents.*


data class ProfileUiState(
    val photo: String = "https://sun9-68.userapi.com/impg/c858336/v858336796/1550a2/u0w7_QtRo1E.jpg?size=1620x2160&quality=96&sign=0c7f4056ca61aebfc33496b4a313890c&type=album",
    val firstName: String = "",
    val lastName: String = "",
    val balance: Float = 0f,
    val menu: List<ProfileItem> = emptyList()
)

@Composable
fun ProfileScreen(navigate: (String) -> Unit, viewModel: ProfileViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    ProfileUi(state = state, navigate = navigate, events = viewModel::obtainEvent)
}


@Composable
fun ProfileUi(state: ProfileUiState, navigate: (String) -> Unit, events: (ProfileEvents) -> Unit) {

    val launcher: ManagedActivityResultLauncher<String, Uri?> =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri: Uri? ->
                events(ChangePhoto(uri))
            }
        )

    LazyColumn(modifier = Modifier.systemBarsPadding(), content = {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = state.photo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(72.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        )

                )
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontStyle = MaterialTheme.typography.labelSmall.fontStyle
                            )
                        ) {
                            append("Change photo")
                        }
                    },
                    onClick = { launcher.launch("image/*") }
                )
                Text(
                    text = "${state.firstName} ${state.lastName}",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
                )
            }
        }
        item {
            ShopButton(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.share_ic), contentDescription = null)
                Text(text = "Upload item", modifier = Modifier.padding(horizontal = 32.dp))
            }
        }
        state.menu.forEach { item ->
            item {
                ProfileItem(item = item) {
                    events(
                        MenuClick(
                            item = item,
                            result = navigate
                        )
                    )
                }
            }
        }
    }
    )
}


sealed class ProfileItemImp(
    override val label: String,
    @get:DrawableRes override val leadingIcon: Int = R.drawable.credit_card_ic,
    override val tailingIcon: @Composable() (() -> Unit)? = {
        Icon(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.right_arrow_ic),
            contentDescription = null
        )
    }
) : ProfileItem {
    object TradeStore : ProfileItemImp(
        label = "Trade store",
    )

    object PaymentMethod : ProfileItemImp(
        label = "PaymentMethod"
    )

    data class Balance(val balance: Float) : ProfileItemImp(
        label = "Balance",
        tailingIcon = {
            Text(text = "$ $balance")
        }
    )

    object TradeHistory : ProfileItemImp(
        label = "Trade History"
    )

    object RestorePurchase : ProfileItemImp(
        label = "Restore purchase",
        leadingIcon = R.drawable.restore_ic
    )

    object Help : ProfileItemImp(
        label = "Help",
        leadingIcon = R.drawable.help_ic,
        tailingIcon = null
    )

    object Logout : ProfileItemImp(
        label = "Log out",
        leadingIcon = R.drawable.log_out_ic,
        tailingIcon = null
    )

    companion object {
        fun getList(balance: Float): List<ProfileItem> {
            return listOf(
                TradeStore,
                PaymentMethod,
                Balance(balance),
                TradeHistory,
                RestorePurchase,
                Help,
                Logout
            )
        }
    }

}


