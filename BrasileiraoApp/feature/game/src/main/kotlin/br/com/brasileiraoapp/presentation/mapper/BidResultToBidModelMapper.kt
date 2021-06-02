package br.com.brasileiraoapp.presentation.mapper

import br.com.brasileiraoapp.presentation.model.BidModel
import br.com.brasileiraoapp.presentation.model.BidType
import br.com.brasileiraoapp.result.BidResult
import br.com.brasileiraoapp.utils.Mapper

class BidResultToBidModelMapper : Mapper<BidResult, BidModel> {
    override fun mapFrom(from: BidResult): BidModel =
        BidModel(from.timeOfGame, from.description, from.teamImg, mapType(from.timeOfGame))

    private fun mapType(timeOfGame: Int): BidType {
        return when {
            timeOfGame < 0 -> {
                BidType.INIT
            }
            timeOfGame in 1..90 -> {
                BidType.NORMAL
            }
            else -> {
                BidType.END
            }
        }
    }
}