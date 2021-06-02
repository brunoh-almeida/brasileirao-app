package br.com.brasileiraoapp.mapper

import br.com.brasileiraoapp.result.TeamResult
import org.junit.Test
import response.TeamResponse
import kotlin.test.assertEquals

class TeamResponseToTeamResultMapperTest {

    private val teamResponseToTeamResultMapper = TeamResponseToTeamResultMapper()

    @Test
    fun mapFrom_TeamResponse_shouldReturnTeamResult() {
        val expected = TeamResult("any-name", "any-url")

        val response = TeamResponse("any-name", "any-url")

        val actual = teamResponseToTeamResultMapper.mapFrom(response)

        assertEquals(expected,actual)
    }
}