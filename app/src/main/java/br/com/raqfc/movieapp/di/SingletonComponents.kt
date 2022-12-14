package br.com.raqfc.movieapp.di

import android.content.Context
import androidx.room.Room
import br.com.raqfc.movieapp.data.local.FavoriteContentsRepository
import br.com.raqfc.movieapp.data.local.cache.FavoriteContentsCacheLocal
import br.com.raqfc.movieapp.data.local.room.AppRoomDatabase
import br.com.raqfc.movieapp.data.network.ContentRepository
import br.com.raqfc.movieapp.data.network.httpservice.RetrofitCapsule
import br.com.raqfc.movieapp.data.network.httpservice.RetrofitService
import br.com.raqfc.movieapp.ui.presentation.view_model.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonComponents {
    private const val BASE_URL = "https://imdb-api.com/pt/API/"

    @Provides
    @Singleton
    fun provideMainViewModel(contentRepository: ContentRepository, favoriteContentsRepository: FavoriteContentsRepository): MainViewModel {
        return MainViewModel(contentRepository, favoriteContentsRepository)
    }

    @Provides
    @Singleton
    fun provideContentRepository(retrofitCapsule: RetrofitCapsule): ContentRepository {
        return ContentRepository(retrofitCapsule)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return Room.databaseBuilder(
            context,
            AppRoomDatabase::class.java, "MovieApp-2022.12-01"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteContentsCacheLocal(roomInstance: AppRoomDatabase): FavoriteContentsCacheLocal {
        return FavoriteContentsCacheLocal(roomInstance)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(cacheLocal: FavoriteContentsCacheLocal): FavoriteContentsRepository {
        return FavoriteContentsRepository(cacheLocal)
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitCapsule(service: RetrofitService): RetrofitCapsule {
        return RetrofitCapsule(service)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}