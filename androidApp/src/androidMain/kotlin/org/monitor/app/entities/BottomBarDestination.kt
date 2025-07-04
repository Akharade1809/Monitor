package org.monitor.app.entities

import androidx.compose.ui.graphics.vector.ImageVector
import org.monitor.app.R

enum class BottomBarDestination(val route: String, val icon : Int, val label: String) {
    Home("home", R.drawable.outline_add_home_24,"Home"),
    Student("student-home", R.drawable.outline_add_row_below_24, "Student"),
    Attendance("attendance", R.drawable.outline_ad_24,"Attendance");

    companion object{
        fun fromRoute(route: String?): BottomBarDestination? =
            values().find { it.route == route }
    }
}