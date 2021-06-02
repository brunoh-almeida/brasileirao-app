package br.com.brasileiraoapp.test.factory

import br.com.brasileiraoapp.result.BidResult
import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

object BidResultFactory {
    fun makeBidResult(
        timeOfGame: Int = Random.nextInt(),
        description: String = Faker().app.name(),
        teamImg: String = Faker().internet.domain()
    )
            = BidResult(
        timeOfGame = timeOfGame,
        description = description,
        teamImg = teamImg
    )
}