package khvatid.shop.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import khvatid.shop.R
import khvatid.shop.ui.components.ShopButton
import khvatid.shop.ui.components.ShopTextField
import khvatid.shop.ui.events.LoginEvents
import khvatid.shop.ui.events.LoginEvents.*

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isErrorEmail: Boolean = false,
    val showPassword: Boolean = true
)

@Composable
fun LoginScreen(navigate: (String) -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    LoginUi(state = state, navigate = navigate, event = viewModel::obtainEvent)

}

@Composable
private fun LoginUi(state: LoginUiState, navigate: (String) -> Unit, event: (LoginEvents) -> Unit) {
    val focusManager = LocalFocusManager.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 44.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.2f))
            Text(text = "Welcome back", style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.weight(0.2f))

            ShopTextField(
                value = state.email,
                onValueChange = { event(EmailChange(it)) },
                placeholder = "Email",
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(MaterialTheme.shapes.extraLarge),
                isError = state.isErrorEmail
            )

            ShopTextField(modifier = Modifier
                .padding(vertical = 16.dp)
                .clip(MaterialTheme.shapes.extraLarge),
                value = state.password,
                onValueChange = { event(PasswordChange(it)) },
                placeholder = "Password",
                keyboardActions = KeyboardActions(onDone = { event(LoginButtonClick(navigate)) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { event(PasswordShowClick) }) {
                        Icon(
                            painter = painterResource(R.drawable.eyeoff_ic),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = if (state.showPassword) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.primary
                        )

                    }
                })

            ShopButton(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                onClick = { event(LoginButtonClick(navigate)) }) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.weight(0.4f))

        }
    }
}