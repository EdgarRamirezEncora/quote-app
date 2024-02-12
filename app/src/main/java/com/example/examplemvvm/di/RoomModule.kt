package com.example.examplemvvm.di

import android.content.Context
import androidx.room.Room
import com.example.examplemvvm.data.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DB_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun providesQuoteDao(db: QuoteDatabase) = db.getQuoteDao()
}