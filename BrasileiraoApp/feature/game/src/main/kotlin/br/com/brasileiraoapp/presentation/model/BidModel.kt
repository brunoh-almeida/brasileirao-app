package br.com.brasileiraoapp.presentation.model

class BidModel(
    val timeOfGame: Int,
    val description: String,
    val teamShieldUrl: String?,
    val type: BidType
)