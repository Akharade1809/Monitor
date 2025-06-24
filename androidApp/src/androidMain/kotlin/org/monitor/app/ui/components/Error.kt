package org.monitor.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.monitor.app.ui.theme.Type

@Composable
fun Error (
    msg : String,
    onTryAgain: () -> Unit = {}
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = msg,
                style = Type.headlineSmall
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedButton(
                onClick = onTryAgain
            ) {
                Text(
                    text = "Retry",
                    style = Type.titleLarge
                )
            }
        }
    }
}