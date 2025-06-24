package org.monitor.shared.extensions

fun String.isValidName() : Boolean {
    val nameRegex = "[a-zA-Z ,.'-]+".toRegex()
    return this.isNotBlank().and(matches(nameRegex))
}

fun String.isValidMobileNumber(): Boolean {
    val numberRegex = "^[6-9]\\d{9}$".toRegex() // Validates 10-digit Indian mobile numbers starting with 6-9
    return this.matches(numberRegex)
}