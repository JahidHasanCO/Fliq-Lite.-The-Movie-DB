package dev.jahidhasanco.movieapp.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.jahidhasanco.movieapp.data.local.AppDataBase
import dev.jahidhasanco.movieapp.data.remote.ApiService
import dev.jahidhasanco.movieapp.data.repository.MovieRepositoryImpl
import dev.jahidhasanco.movieapp.domain.repository.MovieRepository
import dev.jahidhasanco.movieapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): ApiService{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideMovieRepository(apiService: ApiService): MovieRepository{
        return MovieRepositoryImpl(apiService)
    }

    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "MovieDataBase"
    ).build()


    @Singleton
    @Provides
    fun provideYourDao(db: AppDataBase) = db.getMovieDao()

    @Singleton
    @Provides
    fun provideUpComingMoviesDao(db: AppDataBase) = db.getUpcomingMovieDao()

}