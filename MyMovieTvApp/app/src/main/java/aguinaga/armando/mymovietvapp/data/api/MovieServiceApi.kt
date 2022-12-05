package aguinaga.armando.mymovietvapp.data.api

import aguinaga.armando.mymovietvapp.data.model.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServiceApi {

    /*@GET("upcoming?")
    suspend fun getMovies(
        @Query("page") value: Int
    ): Response<RemoteResult?>*/

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getListPopularMovies(
        @Query("api_key") apiKey: String
    ): RemoteResult?

}