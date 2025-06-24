package org.monitor.app.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import org.monitor.app.R
import org.monitor.app.ui.components.ManagementResourceState
import org.monitor.app.ui.navigation.NavItem
import org.monitor.app.ui.theme.designSystem.MainTopBar
import org.monitor.shared.domain.entities.LMSCard
import org.monitor.shared.features.home.HomeScreenContract

@Composable
fun HomeScreen(
    navController: NavController,
    onUiEvent: (HomeScreenContract.Event) -> Unit,
    uiState: StateFlow<HomeScreenContract.State>,
    uiEffect: Flow<HomeScreenContract.Effect>
) {
    LaunchedEffect(Unit) {
        uiEffect.collectLatest { effect ->
            // Handle side effects here
            when (effect){
                HomeScreenContract.Effect.NavigateToAddStudentScreen -> {
                    navController.navigate(NavItem.AddStudentScreen.route)
                }
            }
        }
    }

    val state by uiState.collectAsStateWithLifecycle()


    val modules = listOf(
        LMSCard(title = "Add Student", icon = R.drawable.outline_add_24, trailingIcon = R.drawable.outline_arrow_forward_ios_24, subTitle = "add / delete student", onClick = { onUiEvent(HomeScreenContract.Event.NavigateToAddStudentScreen) }),
        LMSCard(title = "Student List", icon = R.drawable.outline_add_row_below_24, trailingIcon = R.drawable.outline_arrow_forward_ios_24, subTitle = "view students", onClick = {}),
        LMSCard(title = "Attendance", icon = R.drawable.outline_ad_24, trailingIcon = R.drawable.outline_arrow_forward_ios_24, subTitle = "attendance", onClick = {}),
        LMSCard(title = "Notice", icon = R.drawable.outline_add_comment_24, trailingIcon = R.drawable.outline_arrow_forward_ios_24, subTitle = "send message", onClick = {})
    )

    Scaffold(
        topBar = {
            MainTopBar(title = "Home", dayOfWeek = "day")
        },
        containerColor = darkColorScheme().primaryContainer
    ) { paddingValues ->
        ManagementResourceState(
            resourceState = state.homeInfo,
            successView = { homeInfo ->
                Log.d("HomeScreen", "HomeScreen: $homeInfo")
                if (homeInfo != null) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(darkColorScheme().background)
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(modules){ module ->
                            CardItemView(
                                icon = module.icon,
                                title = module.title,
                                trailingIcon = module.trailingIcon,
                                subTitle = module.subTitle,
                                onClick = module.onClick,
                            )
                        }
                    }
                }
            },
            onTryAgain = {},

        )



//        paddingValues ->
//
    }


}



