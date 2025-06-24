package org.monitor.app.ui.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

sealed class NavItem(
    internal val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList(),
    private val deepLinks: List<String> = emptyList()
){
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString { "/" }
    }

    val args = navArgs.map {
        navArgument(it.key){type = it.navType}
    }

    val links: List<NavDeepLink> = deepLinks.map {
        navDeepLink { uriPattern = it }
    }


    data object AppParent : NavItem("app-parent", navArgs = listOf(NavArg.DESTINATION))
    data object Home : NavItem("home")
    data object WebView : NavItem("web-view", navArgs = listOf(NavArg.WEBVIEW_URL))
    data object SplashScreen : NavItem("splash-screen")
    data object AddStudentScreen : NavItem("add-student-screen")
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    HOME("home", NavType.StringType),
    DESTINATION("destination", NavType.StringType),
    WEBVIEW_URL("url", NavType.StringType),
}

fun NavController.navigateWithBundle(
    route: String,
    args : Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
){
    val nodeId = graph.findNode(route = route)?.id
    if (nodeId != null){
        navigate(
            resId = nodeId,
            args = args,
            navOptions = navOptions,
            navigatorExtras = navigatorExtras
        )
    }
}

fun navigateToWebView(
    link: String,
    triggerRoute : String? = null,
    navController: NavController
){
    val encodeUrl = Uri.encode(link)
    navController.navigate(NavItem.WebView.route.replace("{url}",encodeUrl)){
        if (triggerRoute != null){
            popUpTo(triggerRoute){inclusive = true}
        }
    }
}

data class BottomNavItems(
    val name : String,
    val route : String,
    val icon : Int
)