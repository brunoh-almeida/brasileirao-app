package br.com.brasileiraoapp.presentation.mapper

import br.com.brasileiraoapp.presentation.model.TeamModel
import br.com.brasileiraoapp.result.TeamResult
import br.com.brasileiraoapp.utils.Mapper

class TeamResultToTeamMapper : Mapper<TeamResult, TeamModel> {
    override fun mapFrom(from: TeamResult) =
        TeamModel(name = from.name, shieldUrl = from.shieldUrl)
}