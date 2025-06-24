package org.monitor.shared.data.local.datasource

interface AppInformationLocalDataSource {
    val appName : String
    val appPackageName : String
    val appVersion : String
    val appBuildId : String
    val buildType : String
    val basUrl : String
}