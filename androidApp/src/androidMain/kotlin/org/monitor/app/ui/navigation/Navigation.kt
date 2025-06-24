package org.monitor.app.ui.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import org.monitor.app.ui.BaseActivity
import org.monitor.app.ui.home.HomeScreen
import org.monitor.app.ui.splash.SplashScreen
import org.monitor.app.ui.student.AddStudentScreen
import org.monitor.app.ui.theme.baseDark
import org.monitor.shared.base.executor.IExecutorScope
import org.monitor.shared.features.AppContract
import org.monitor.shared.features.app.mvi.AppViewModel
import org.monitor.shared.features.home.HomeScreenViewModel
import org.monitor.shared.features.splash.SplashViewModel
import org.monitor.shared.features.student.AddStudentViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val viewModels = mutableListOf<IExecutorScope>()
    val activity = LocalActivity.current as? BaseActivity
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    DisposableEffect(activity) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                viewModels.forEach { it.cancel() }
            }
        }
        activity?.lifecycle?.addObserver(observer)
        onDispose {
            activity?.lifecycle?.removeObserver(observer)
        }
    }

    println("Navigation : creating appViewmodel instance via koin")
    val appViewModel : AppViewModel = koinInject()

    println("Navigation : $appViewModel")
    val appParentDestination = "app-parent"

    LaunchedEffect(key1 = Unit) {
        appViewModel.effect.collectLatest { effect ->
            when(effect){
                AppContract.Effect.NavigateToBrowse -> {
                    navController.navigate(NavItem.AppParent.route.replace(
                        oldValue = "{${NavArg.DESTINATION.key}}",
                        newValue = appParentDestination
                    )){
                        popUpTo(NavItem.AppParent.route) {inclusive = true }
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                Snackbar(
                    containerColor = baseDark,
                    snackbarData = snackbarData
                )
            }
        }
    ) { innerpadding ->
        Modifier.systemBarsPadding()
        NavHost(
            navController = navController,
            startDestination = NavItem.SplashScreen.route
        ){
            composable(NavItem.SplashScreen.route) {
                val vmSplash : SplashViewModel = koinInject()
                if (viewModels.contains(vmSplash).not()) {
                    viewModels.add(vmSplash)
                }
                SplashScreen(
                    parentNavController = navController,
                    uiEffect = vmSplash.effect
                )
            }

            composable(NavItem.Home.route) {
                val vmHome : HomeScreenViewModel = koinInject()
                if (viewModels.contains(vmHome).not()) {
                    viewModels.add(vmHome)
                }
                HomeScreen(
                    navController = navController,
                    uiEffect = vmHome.effect,
                    uiState = vmHome.uiState,
                    onUiEvent = { event -> vmHome.setEvent(event)  },
                )
            }

            composable(NavItem.AddStudentScreen.route) {
                val vmAddStudent : AddStudentViewModel = koinInject()
                if (viewModels.contains(vmAddStudent).not()) {
                    viewModels.add(vmAddStudent)
                }
                AddStudentScreen(
                    navController = navController,
                    uiEffect = vmAddStudent.effect,
                    uiState = vmAddStudent.uiState,
                    onUiEvent = {event -> vmAddStudent.setEvent(event)}
                )
            }
        }

    }
}