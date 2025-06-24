package org.monitor.app.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import org.monitor.app.ui.navigation.NavItem
import org.monitor.app.ui.theme.AppTheme
import org.monitor.app.ui.theme.Type
import org.monitor.app.ui.theme.baseDark
import org.monitor.app.ui.theme.surfaceDark

import org.monitor.shared.features.splash.SplashScreenContract

@Composable
fun SplashScreen(
    parentNavController: NavController,
    uiEffect: Flow<SplashScreenContract.Effect>,
    modifier: Modifier = Modifier
){

    LaunchedEffect(key1 = Unit) {
        uiEffect.collectLatest { effect->
            when(effect){
                SplashScreenContract.Effect.NavigateToHome -> {
                    parentNavController.navigate(NavItem.Home.route){
                        popUpTo(NavItem.SplashScreen.route) {inclusive = true}
                    }
                }
            }
        }
    }

    val gradient = Brush.verticalGradient(
        listOf(
            baseDark,
            surfaceDark,
            baseDark

        )
    )

    Box(modifier
        .fillMaxSize()
        .background(gradient),
        contentAlignment = Alignment.Center
    ){
        Text(
            "Monitor",
            style = Type.titleLarge,

        )
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    AppTheme {
        SplashScreen(
            parentNavController = NavController(
                context = LocalContext.current
            ),
            uiEffect = flowOf(),
            modifier = Modifier
        )
    }
}