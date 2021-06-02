package br.com.brasileiraoapp.mapper

import br.com.brasileiraoapp.result.BidResult
import org.junit.Test
import response.BidResponse
import kotlin.test.assertEquals

class BidResponseToBidResultMapperTest {
    private val bidResultMapper = BidResponseToBidResultMapper()

    @Test
    fun mapFrom_BidResponse_shouldReturnBidResult() {
        val expected = BidResult(10, "any", teamImg = "any")

        val response = BidResponse(10, "any", teamImage = "any")

        val actual = bidResultMapper.mapFrom(response)

        assertEquals(expected, actual)
    }
}