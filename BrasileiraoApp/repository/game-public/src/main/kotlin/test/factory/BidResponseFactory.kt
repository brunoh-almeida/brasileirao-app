package test.factory

import io.github.serpro69.kfaker.Faker
import response.BidResponse
import response.GameResponse
import kotlin.random.Random

object BidResponseFactory {
    fun makeBidResponse(
        timeOfGame: Int = Random.nextInt(),
        descriptionOfBid: String = Faker().name.name(),
        teamImage: String = Faker().internet.domain()
    ) =
        BidResponse(
            timeOfGame = timeOfGame,
            descriptionOfBid = descriptionOfBid,
            teamImage = teamImage
        )


    fun makeBidResponseList(numberOfBids: Int = 2): MutableList<BidResponse> {
        val gameList = mutableListOf<BidResponse>()
        for (x in 0 until numberOfBids) {
            gameList.add(makeBidResponse())
        }
        return gameList
    }

}