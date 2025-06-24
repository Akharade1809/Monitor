package org.monitor.app.ui.theme.designSystem


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.monitor.app.ui.theme.AppTheme


@Composable
fun MainTopBar(
    title: String,
    dayOfWeek: String,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .testTag("${title}AppBar"),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
            )
            Column {
                Text(
                    text = dayOfWeek,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White.copy(alpha = 0.8f))
                )
            }
        }
    }
}

@Composable
@Preview
fun MainAppBarPreview(){
    AppTheme {
        MainTopBar("Home", dayOfWeek = "sun")
    }
}