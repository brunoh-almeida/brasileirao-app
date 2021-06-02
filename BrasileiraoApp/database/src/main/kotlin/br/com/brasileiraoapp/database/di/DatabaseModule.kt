package br.com.brasileiraoapp.database.di

import androidx.room.Room
import br.com.brasileiraoapp.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "database"
        ).build()
    }
    single {
        get<AppDatabase>().gameDao()
    }
}