package com.example.nextfilm.di

import com.example.nextfilm.data.repository.NextFilmRepository
import com.example.nextfilm.data.sources.remote.NextFilmApi
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
        api: NextFilmApi
    ) = NextFilmRepository(api)

    @Singleton
    @Provides
    fun provideMovieApi(

    ): NextFilmApi {
        return Retrofit // aqui o Retrofit vai fazer a implementação da interface que a gente criou
            .Builder() // cria o “motor” do Retrofit
            .addConverterFactory(GsonConverterFactory.create()) //converte JSON ⇄ objetos Kotlin
            .baseUrl(BASE_URL) // URL base da API
            .build() // cria a instância do Retrofit
            .create(NextFilmApi::class.java) //Retrofit cria uma classe escondida que implementa NextFilmApi
    }
}