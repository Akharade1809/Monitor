package org.monitor.app.ui.theme.designSystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.monitor.app.ui.theme.AppTheme
import org.monitor.app.ui.theme.Shape
import org.monitor.app.ui.theme.dim_gray
import org.monitor.app.ui.theme.light_gray
import org.monitor.app.ui.theme.medium_gray

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = "",
    isError : Boolean = false,
    errorMsg : String? = ""
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = light_gray) },
        placeholder = { Text(text = placeholder, color = medium_gray) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = dim_gray,
            unfocusedBorderColor = dim_gray.copy(alpha = 0.6f),
            cursorColor = Color.White,
            focusedLabelColor = light_gray,
            unfocusedLabelColor = medium_gray,
        ),
        shape = Shape.medium,
        singleLine = true
    )
}


@Composable
@Preview
fun InputTextFieldPreview(){
    AppTheme { 
        InputTextField(
            value = TODO(),
            onValueChange = TODO(),
            label = TODO(),
            modifier = TODO(),
            leadingIcon = TODO(),
            trailingIcon = TODO(),
            placeholder = TODO()
        )
    }
}


