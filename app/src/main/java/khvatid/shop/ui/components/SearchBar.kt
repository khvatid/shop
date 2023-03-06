package khvatid.shop.ui.components


import android.app.role.RoleManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


private const val PLACEHOLDER_STRING = "Movie, tv-shows, anime, cartoon."

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = PLACEHOLDER_STRING) },
        shape = MaterialTheme.shapes.extraLarge,
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch()
        })
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(
        modifier = modifier
            .border(
                border = BorderStroke(
                    width = 1.dp, color = MaterialTheme.colorScheme.outline
                ), shape = MaterialTheme.shapes.extraLarge
            )
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 4.dp, bottom = 16.dp),
            text = PLACEHOLDER_STRING,
            color = color
        )
        Box(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.padding(12.dp),
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = color
            )
        }
    }
}
