package dev.jahidhasanco.movieapp.data.model.movie


import com.google.gson.annotations.SerializedName
import dev.jahidhasanco.movieapp.domain.model.movie.Movie

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

fun ResultDTO.toDomainMovie(): Movie{
    return Movie(
        adult = this.adult?:false,
        genreIds = this.genreIds?: emptyList<Int>(),
        originalTitle = this.originalTitle?:"",
        posterPath = this.posterPath?:"",
        releaseDate = this.releaseDate?:"",
        backdropPath = this.backdropPath?:""
    )
}