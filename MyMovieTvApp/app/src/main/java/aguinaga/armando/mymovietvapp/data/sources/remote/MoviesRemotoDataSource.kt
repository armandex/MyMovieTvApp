package aguinaga.armando.mymovietvapp.data.sources.remote

import aguinaga.armando.mymovietvapp.data.api.MovieServiceApi
import aguinaga.armando.mymovietvapp.data.model.RemoteResult
import aguinaga.armando.mymovietvapp.data.sources.MoviesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRemotoDataSource @Inject constructor(
    private val moviesServiceApi: MovieServiceApi
): MoviesDataSource {

    override suspend fun getListPopularMovies(
        apikey: String
    ): RemoteResult?  = withContext(Dispatchers.IO) {
        val response = moviesServiceApi.getListPopularMovies(apikey)
        //Timber.e("getMoviesFromBackend"+response?.results?.get(0)?.title)
         if (response?.results?.isNotEmpty() == true)
             return@withContext response
         else
             return@withContext null

    }

    /*override suspend fun getMovies(page: Int): List<ResponseMovies.Movie>? {
        return null
    }

    override suspend fun getMovieById(page: Int): ResponseMovies.Movie? {
        return null
    }

    override suspend fun saveMovies(lista: List<ResponseMovies.Movie>) {

    }

    override suspend fun saveMovie(movie: ResponseMovies.Movie) {

    }*/
}