package br.com.eduardotanaka.pokedex.di

import br.com.eduardotanaka.pokedex.BuildConfig
import br.com.eduardotanaka.pokedex.network.HttpRequestInterceptor
import br.com.eduardotanaka.pokedex.network.PokedexService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * colocar as interfaces de chamadas do retrofit aqui
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpRequestInterceptor(): HttpRequestInterceptor = HttpRequestInterceptor()

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpRequestInterceptor: HttpRequestInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpRequestInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val gson = GsonBuilder()
        return gson.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.PokedexUrl)
            .addConverterFactory(
                GsonConverterFactory
                    .create(gson)
            )
        return retrofit.build()
    }

    @Singleton
    @Provides
    fun providePokedexService(retrofit: Retrofit): PokedexService =
        retrofit.create(PokedexService::class.java)
}
