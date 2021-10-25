package dev.jahidhasanco.FliqLite.data.model.movie


import com.google.gson.annotations.SerializedName
import dev.jahidhasanco.FliqLite.data.local.entity.PopularMovieEntity
import dev.jahidhasanco.FliqLite.data.local.entity.TopRatedMovieEntity
import dev.jahidhasanco.FliqLite.data.local.entity.UpcomingMovieEntity

data class ResultDTO(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)


fun ResultDTO.toUpComingMovieEntity(): UpcomingMovieEntity {
    return UpcomingMovieEntity(
        originalTitle = this.originalTitle ?: "",
        releaseDate = this.releaseDate ?: "",
        backdropPath = this.backdropPath ?: "",
        idMovie = this.id
    )
}


fun ResultDTO.toPopularMovieEntity(): PopularMovieEntity {
    return PopularMovieEntity(
        originalTitle = this.originalTitle ?: "",
        releaseDate = this.releaseDate ?: "",
        posterPath = this.posterPath ?: "",
        idMovie = this.id
    )
}

fun ResultDTO.toTopRatedMovieEntity(): TopRatedMovieEntity {
    return TopRatedMovieEntity(
        originalTitle = this.originalTitle ?: "",
        releaseDate = this.releaseDate ?: "",
        posterPath = this.posterPath ?: "",
        idMovie = this.id
    )
}