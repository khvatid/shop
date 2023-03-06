package khvatid.shop.ui.screens.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import khvatid.shop.R
import khvatid.shop.ui.components.ShopButton
import khvatid.shop.ui.components.ShopTextField
import khvatid.shop.ui.events.SignupEvents
import khvatid.shop.ui.events.SignupEvents.*


data class SignupScreenState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val isErrorEmail: Boolean = false,
) {
}

@Composable
fun SignupScreen(viewModel: SignupViewModel = hiltViewModel(), navigate: (String) -> Unit) {
    val state by viewModel.uiState.collectAsState()
    SignupUi(state = state, event = viewModel::obtainEvent, navigate)
}

@Composable
private fun SignupUi(
    state: SignupScreenState,
    event: (SignupEvents) -> Unit,
    navigate: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 44.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.2f))

            Text(text = "Sign up", style = MaterialTheme.typography.displaySmall)

            Spacer(modifier = Modifier.weight(0.2f))

            ShopTextField(
                value = state.firstName,
                onValueChange = { event(FirstNameChange(it)) },
                placeholder = "First name",
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            )

            ShopTextField(
                value = state.lastName, onValueChange = { event(LastNameChange(it)) },
                placeholder = "Last name",
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            )

            ShopTextField(
                value = state.email,
                onValueChange = { event(EmailChange(it)) },
                placeholder = "Email",
                keyboardActions = KeyboardActions(onDone = { event(SignupButtonClick(navigate)) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                isError = state.isErrorEmail,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
            )

            ShopButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                onClick = { event(SignupButtonClick(navigate)) }) {
                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            ClickableText(
                modifier = Modifier
                    .padding(vertical = 0.dp)
                    .fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)) {

                        append("Already have an account? ")
                        withStyle(
                            MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary)
                                .toSpanStyle()
                        ) {
                            append(" Log in")
                        }
                    }
                },
                style = MaterialTheme.typography.labelSmall,
                onClick = { event(LoginButtonClick(navigate)) }
            )
            Spacer(modifier = Modifier.weight(0.2f))
            TextButton(onClick = { event(GoogleButtonClick(navigate)) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.google_ic),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "  Sign up with Google",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}