package org.monitor.app.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.monitor.app.entities.BottomBarDestination

@Composable
fun MonitorBottomBar(
    currentRoute : String?,
    onItemClick : (String) -> Unit
){
    NavigationBar {
        BottomBarDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = {onItemClick(destination.route)},
                icon = { Icon(
                    painter = painterResource(destination.icon),
                    contentDescription = destination.label
                )},
                label = { Text(
                    text = destination.label
                ) }
            )
        }
    }
}