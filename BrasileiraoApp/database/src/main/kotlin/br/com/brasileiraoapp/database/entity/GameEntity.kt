package br.com.brasileiraoapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
class GameEntity(
    @PrimaryKey val id: Long,
    val awayGoals: Int,
    val gameDate: String,
    val homeGoals: Int,
    val round: Int,
    val stadium: String,
    val teamAwayName: String,
    val teamAwayShieldUrl: String,
    val teamHomeShieldUrl: String,
    val teamHomeName: String,
)