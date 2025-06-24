package org.monitor.shared

class IosPlatform : Platform {
    override val name: String="ios"
}

actual fun getPlatform(): Platform = IosPlatform()