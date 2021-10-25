package dev.jahidhasanco.movieapp.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.jahidhasanco.movieapp.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.movieapp.data.model.movie.toPopularMovieEntity
import dev.jahidhasanco.movieapp.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException


class SeeAllPopularMoviePageSource
constructor(private val apiService: ApiService): PagingSource<Int, PopularMovieEntity>()
{

    private val DEFAULT_PAGE_INDEX= 1


    override fun getRefreshKey(state: PagingState<Int, PopularMovieEntity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieEntity> {

        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = apiService.getPopularMovies("",page).results.map {
                it.toPopularMovieEntity()
            }
            LoadResult.Page(
                response,
                prevKey = if(page == DEFAULT_PAGE_INDEX) null else page-1,
                nextKey = if(response.isEmpty()) null else page+1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }

    }

}