package com.riyaldi.core.di

import android.content.Context
import androidx.room.Room
import com.riyaldi.core.data.source.local.room.GameDao
import com.riyaldi.core.data.source.local.room.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("gameKuy".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(context, GameDatabase::class.java, "Game.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideGameDao(database: GameDatabase): GameDao = database.gameDao()
}