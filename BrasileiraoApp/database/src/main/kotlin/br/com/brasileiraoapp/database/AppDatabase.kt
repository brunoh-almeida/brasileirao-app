package br.com.brasileiraoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.brasileiraoapp.database.dao.GameDao
import br.com.brasileiraoapp.database.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}