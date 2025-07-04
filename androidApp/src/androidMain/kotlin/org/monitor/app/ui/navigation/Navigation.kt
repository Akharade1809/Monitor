package org.monitor.app.ui.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import org.monitor.app.entities.BottomBarDestination
import org.monitor.app.ui.BaseActivity
import org.monitor.app.ui.components.MonitorBottomBar
import org.monitor.app.ui.student_home.StudentHomeScreen
import org.monitor.app.ui.splash.SplashScreen
import org.monitor.app.ui.student_home.AddStudentScreen
import org.monitor.app.ui.student_home.GetStudentListScreen
import org.monitor.app.ui.theme.baseDark
import org.monitor.shared.base.executor.IExecutorScope
import org.monitor.shared.features.AppContract
import org.monitor.shared.features.app.mvi.AppViewModel
import org.monitor.shared.features.student_home.StudentHomeScreenViewModel
import org.monitor.shared.features.splash.SplashViewModel
import org.monitor.shared.features.student.AddStudentViewModel
import org.monitor.shared.features.student.GetStudentListViewModel

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

    val appViewModel : AppViewModel = koinInject()

    val appParentDestination = "app-parent"

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottmBar = BottomBarDestination.fromRoute(currentRoute) != null

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
        },
        bottomBar = {
            if (showBottmBar){
                MonitorBottomBar(currentRoute = currentRoute) { route ->
                    navController.navigate(route){
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }

                }
            }
        }
    ) { innerpadding ->
        NavHost(
            navController = navController,
            startDestination = "splash-screen",
            modifier = Modifier.padding(innerpadding)
        ){

            composable("splash-screen") {
                println("Calling SplashScreen")
                val vmSplash : SplashViewModel = koinInject()
                if (viewModels.contains(vmSplash).not()) {
                    viewModels.add(vmSplash)
                }
                SplashScreen(
                    parentNavController = navController,
                    uiEffect = vmSplash.effect
                )
            }
            composable("home") {
                val vmHome : StudentHomeScreenViewModel = koinInject()
                if (viewModels.contains(vmHome).not()) {
                    viewModels.add(vmHome)
                }
                StudentHomeScreen(
                    navController = navController,
                    uiEffect = vmHome.effect,
                    uiState = vmHome.uiState,
                    onUiEvent = { event -> vmHome.setEvent(event) }
                )
            }
            composable("add-student-screen") {
                println("calling AddStudentScreen")
                val vmAddStudent : AddStudentViewModel = koinInject()
                if (viewModels.contains(vmAddStudent).not()) {
                    viewModels.add(vmAddStudent)
                }
                AddStudentScreen(
                    navController = navController,
                    uiEffect = vmAddStudent.effect,
                    uiState = vmAddStudent.uiState,
                    onUiEvent = { event -> vmAddStudent.setEvent(event) }
                )
            }
            composable("get-student-list-screen") {
                val vmStudentList : GetStudentListViewModel = koinInject()
                if (viewModels.contains(vmStudentList).not()){
                    viewModels.add(vmStudentList)
                }
                GetStudentListScreen(
                    navController = navController,
                    uiEffect = vmStudentList.effect,
                    uiState = vmStudentList.uiState,
                    onUiEvent = { event ->  vmStudentList.setEvent(event) }
                )
            }
        }

    }
}

fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit,
){
    composable(
        route = navItem.route,
        arguments = navItem.args,
        deepLinks = navItem.links
    ){
        content(it)
    }
}