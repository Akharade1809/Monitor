package org.monitor.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform