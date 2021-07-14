package com.example.marvelapi.di

import com.example.marvelapi.data.remote.MarvelService
import com.example.marvelapi.utils.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun providesRetrofit(moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MarvelService::class.java)


}