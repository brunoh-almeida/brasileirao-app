package br.com.brasileiraoapp.mapper

import response.TeamResponse
import br.com.brasileiraoapp.result.TeamResult
import br.com.brasileiraoapp.utils.Mapper

class TeamResponseToTeamResultMapper: Mapper<TeamResponse, TeamResult> {
    override fun mapFrom(from: TeamResponse): TeamResult =
        TeamResult(
            name = from.name,
            shieldUrl = from.shieldUrl
        )
}