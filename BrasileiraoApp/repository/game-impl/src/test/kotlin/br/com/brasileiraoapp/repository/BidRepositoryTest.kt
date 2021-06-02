package br.com.brasileiraoapp.repository

import br.com.brasileiraoapp.api.BidApi
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import repository.BidRepository
import response.BidResponse

class BidRepositoryTest {

    private val bidApi = mockk<BidApi>()

    private val bidRepository: BidRepository = BidRepositoryImpl(bidApi)

    @Test
    fun getBidOfGame_ResultSuccess() {
        val expected = listOf(
            BidResponse(1, "any", "any-url"),
            BidResponse(2, "any", "any-url"),
            BidResponse(3, "any", "any-url"),
        )

        prepareScenario(bidResponse = expected)

        runBlocking {
            val actual = bidRepository.getBidsOfGame(1).handleResult()

            assert(expected == actual)
        }
    }

    @Test
    fun getBidOfGame_ResultSuccess_EmptyList() {
        val expected = listOf<BidResponse>()

        prepareScenario(bidResponse = expected)

        runBlocking {
            val actual = bidRepository.getBidsOfGame(1).handleResult()

            assert(expected == actual)
        }
    }

    @Test
    fun getBidOfGame_ResultError() {
        coEvery {
            bidApi.getBidsOfGame(any())
        } throws Exception("msg")

        val expected = NetworkResponse.Error(NetworkError("msg", null))

        runBlocking {
            val call = bidRepository.getBidsOfGame(10)
            val result = call.mapError { it }
            assert(result is NetworkResponse.Error)
            assertEquals(expected, result)
        }
    }

    private fun prepareScenario(
        bidResponse: List<BidResponse> = listOf()
    ) {
        coEvery {
            bidApi.getBidsOfGame(any())
        } returns bidResponse
    }


}