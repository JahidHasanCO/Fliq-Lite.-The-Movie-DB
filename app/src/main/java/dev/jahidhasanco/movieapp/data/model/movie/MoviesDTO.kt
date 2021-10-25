package dev.jahidhasanco.movieapp.data.model.movie


import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("dates")
    val dates: DatesDTO,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)