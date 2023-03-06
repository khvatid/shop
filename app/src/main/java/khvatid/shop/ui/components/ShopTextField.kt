package khvatid.shop.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khvatid.shop.ui.theme.ShopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable() (() -> Unit)? = null,
    isError : Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val leading: @Composable() (() -> Unit)? =
        if (trailingIcon == null) null else { {} }
    BasicTextField(modifier = modifier.height(42.dp),
        value = value,
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = if (!interactionSource.collectIsFocusedAsState().value) {
                TextAlign.Center
            } else TextAlign.Start
        ),
        interactionSource = interactionSource,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        visualTransformation = visualTransformation,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                shape = MaterialTheme.shapes.extraLarge,
                value = value,
                innerTextField = {
                    Row(
                    ) {
                        Box(
                            modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
                        ) {
                            innerTextField()
                        }
                    }
                },
                enabled = true,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                placeholder = {
                    Row() {
                        Text(
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            text = placeholder,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                },
                isError = isError,
                trailingIcon = trailingIcon,
                leadingIcon = leading,
                contentPadding = PaddingValues(7.dp),
                colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent)
            )
        }
    )
}

@Preview
@Composable
private fun AuthTextFieldPreview() {
    ShopTheme {
        Column {
            ShopTextField(
                value = "",
                placeholder = "First name",
                onValueChange = {},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Place,
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                })
            ShopTextField(value = "", placeholder = "First name", onValueChange = {})
        }
    }
}


