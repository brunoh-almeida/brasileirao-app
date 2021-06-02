package br.com.brasileiraoapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.brasileiraoapp.database.entity.GameEntity

@Dao
interface GameDao {
    @Query("SELECT * FROM game WHERE round = :round")
    suspend fun getGamesByRound(round: Int): List<GameEntity>?

    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getGame(id: Long): GameEntity?

    @Insert
    fun insertAll(vararg users: GameEntity)

    @Delete
    fun delete(user: GameEntity)
}