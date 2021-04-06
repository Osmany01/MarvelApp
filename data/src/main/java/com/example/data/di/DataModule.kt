package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.api.MarvelDataSource
import com.example.data.api.MarvelInterceptor
import com.example.data.api.RemoteDataSource
import com.example.data.api.services.characters.CharactersService
import com.example.data.local.LocalDataSource
import com.example.data.local.RoomDataSource
import com.example.data.local.dao.MarvelDatabase
import com.example.data.repositories.characters.CharactersRepositoryImpl
import com.example.domain.domain.repositories.CharactersRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    companion object {

        const val BASE_URL_MARVEL_API = "https://gateway.marvel.com/"
    }
    val clientBuilder = OkHttpClient.Builder()

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_MARVEL_API)
            .client(clientBuilder.addInterceptor(MarvelInterceptor(clientBuilder)).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesCharactersService(retrofit: Retrofit): CharactersService =
        retrofit.create(CharactersService::class.java)

    @Provides
    @Singleton
    fun providesRoomDataBase(application: Application): MarvelDatabase =
        Room.databaseBuilder(application, MarvelDatabase::class.java, "marvel-db").build()

    @Provides
    @Singleton
    fun providesLocalDataSource(db: MarvelDatabase) : LocalDataSource =
        RoomDataSource(db)

    @Provides
    @Singleton
    fun providesRemoteDataSource(service: CharactersService): RemoteDataSource =
        MarvelDataSource(service)

    @Provides
    @Singleton
    fun providesNotesRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): CharactersRepository =
        CharactersRepositoryImpl(localDataSource, remoteDataSource)
}