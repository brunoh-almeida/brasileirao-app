package br.com.brasileiraoapp.mapper

import br.com.brasileiraoapp.result.BidResult
import br.com.brasileiraoapp.utils.Mapper
import response.BidResponse

class BidResponseToBidResultMapper : Mapper<BidResponse, BidResult> {
    override fun mapFrom(from: BidResponse) = BidResult(
        from.timeOfGame,
        from.descriptionOfBid,
        from.teamImage
    )
}