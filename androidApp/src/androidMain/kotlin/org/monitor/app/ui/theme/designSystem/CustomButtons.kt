package org.monitor.app.ui.theme.designSystem

import android.media.audiofx.AudioEffect.Descriptor
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.monitor.app.ui.theme.AppTheme
import org.monitor.app.ui.theme.Shape
import org.monitor.app.ui.theme.dim_gray
import org.monitor.app.ui.theme.medium_gray
import org.monitor.app.ui.theme.vib_green

@Composable
fun SubmitButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    @DrawableRes icon: Int? = null,
    iconDescriptor: String? = "",
    isEnabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = vib_green,
            contentColor = Color.White,
            disabledContainerColor = dim_gray,
            disabledContentColor = medium_gray
        ),
        shape = Shape.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = iconDescriptor,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview
private fun SubmitButtonPreview(){
    AppTheme {
        SubmitButton(
            text = "Submit",
            onClick = {}
        )
    }
}