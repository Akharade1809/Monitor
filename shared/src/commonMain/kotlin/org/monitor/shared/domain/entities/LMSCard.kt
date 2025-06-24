package org.monitor.shared.domain.entities


data class LMSCard(
    val title: String,
    val icon: Int,
    val trailingIcon: Int?,
    val subTitle : String?,
    val onClick: () -> Unit
)
