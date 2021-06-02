package br.com.brasileiraoapp.usecase

import br.com.brasileiraoapp.mapper.BidResponseToBidResultMapper
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import br.com.brasileiraoapp.result.BidResult
import br.com.brasileiraoapp.test.factory.BidResultFactory
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import repository.BidRepository
import response.BidResponse
import test.factory.BidResponseFactory
import kotlin.test.assertEquals

class GetBidsOfGameUseCaseTest {


    private val bidsRepository = mockk<BidRepository>()

    private val bidResultMapper = mockk<BidResponseToBidResultMapper>()

    val getBidsOfGameUseCase = GetBidsOfGameUseCaseImpl(bidsRepository, bidResultMapper)

    @Test
    fun invoke_shouldReturnSuccess() {
        val mapResult = BidResultFactory.makeBidResult()
        val expected = listOf(mapResult, mapResult)
        prepareScenario(bidMapperResult = mapResult)


        runBlocking {
            var result: List<BidResult> = listOf()
            getBidsOfGameUseCase(1).mapSuccess {
               result = it
            }

            assertEquals(expected, result)
        }
    }

    @Test
    fun invoke_verifyGameMapperCall() {
        prepareScenario()
        runBlocking {
            getBidsOfGameUseCase.invoke(2)
            verify(exactly = 2) {
                bidResultMapper.mapFrom(any())
            }
        }
    }

    @Test
    fun invoke_shouldReturnError() {
        prepareScenario(
            repositoryResult = NetworkResponse.Error(NetworkError(null, null))
        )

        val expected = Result.Error(ResultError.UnknownError())

        runBlocking {
            val actual = getBidsOfGameUseCase(1)
            assertEquals(expected,actual)
        }
    }
    private fun prepareScenario(
        bidMapperResult: BidResult = BidResultFactory.makeBidResult(),
        repositoryResult: NetworkResponse<List<BidResponse>, NetworkError> = NetworkResponse.Success(
            BidResponseFactory.makeBidResponseList()
        )
    ) {
        every {
            bidResultMapper.mapFrom(any())
        } returns bidMapperResult
        coEvery {
            bidsRepository.getBidsOfGame(any())
        } returns repositoryResult
    }
}