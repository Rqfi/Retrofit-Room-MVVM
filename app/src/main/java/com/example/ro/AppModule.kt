package com.example.ro

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room.databaseBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return ApiConfig.getService()
    }
}
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideAppDao(application: Application?): AppDao {
        val database: AppDatabase =
            databaseBuilder(application!!, AppDatabase::class.java, "app_database")
                .build()
        return database.appDao()
    }
}
