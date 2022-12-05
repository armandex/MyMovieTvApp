package aguinaga.armando.mymovietvapp.data.sources

import aguinaga.armando.mymovietvapp.data.model.RemoteResult

interface MoviesDataSource {

    suspend fun getListPopularMovies(
        apikey: String
    ): RemoteResult?

   /* suspend fun getMovies(
        page: Int
    ): List<RemoteResult.results>?

    suspend fun getMovieById(
        idMovie: Int
    ): ResponseMovies.Movie?

    suspend fun saveMovies(lista: List<ResponseMovies.Movie>)

    suspend fun saveMovie(movie: ResponseMovies.Movie)*/

}