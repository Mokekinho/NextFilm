package com.example.nextfilm.di

import android.graphics.Movie
import com.example.nextfilm.data.repository.MovieRepository
import com.example.nextfilm.data.sources.remote.MovieApi
import com.example.nextfilm.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: MovieApi
    ) = MovieRepository(api)

    @Singleton
    @Provides
    fun provideMovieApi(

    ): MovieApi {
        return Retrofit // aqui o Retrofit vai fazer a implementação da interface que a gente criou
            .Builder() // cria o “motor” do Retrofit
            .addConverterFactory(GsonConverterFactory.create()) //converte JSON ⇄ objetos Kotlin
            .baseUrl(BASE_URL) // URL base da API
            .build() // cria a instância do Retrofit
            .create(MovieApi::class.java) //Retrofit cria uma classe escondida que implementa MovieApi
    }
}