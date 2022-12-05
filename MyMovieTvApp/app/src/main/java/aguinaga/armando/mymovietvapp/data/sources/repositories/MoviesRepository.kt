package aguinaga.armando.mymovietvapp.data.sources.repositories

import aguinaga.armando.mymovietvapp.data.model.RemoteResult
import aguinaga.armando.mymovietvapp.data.sources.MoviesDataSource
import aguinaga.armando.mymovietvapp.data.sources.remote.MoviesRemotoDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    //private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemotoDataSource: MoviesRemotoDataSource
) : MoviesDataSource {

    var forzarActualizacion = false

    override suspend fun getListPopularMovies(apikey: String): RemoteResult? {
        return obtenerPeliculasEnCarteleraRemoto(apikey)
    }

    /*override suspend fun getMovies(page: Int): List<ResponseMovies.Movie>? {
        return if (forzarActualizacion) {
            obtenerPeliculasEnCarteleraRemoto(page)?.results
        } else {
            val moviesLocal = moviesLocalDataSource.getMovies(page)
            if (moviesLocal.isNullOrEmpty()) {
                obtenerPeliculasEnCarteleraRemoto(page)?.results
            } else {
                return moviesLocal
            }
        }
    }

    override suspend fun getMovieById(idMovie: Int): ResponseMovies.Movie {
        return moviesLocalDataSource.getMovieById(idMovie)
    }

    override suspend fun saveMovies(lista: List<ResponseMovies.Movie>) {
        return moviesLocalDataSource.saveMovies(lista)
    }

    override suspend fun saveMovie(movie: ResponseMovies.Movie) {
        return moviesLocalDataSource.saveMovie(movie)
    }*/

    suspend fun obtenerPeliculasEnCarteleraRemoto(apikey: String): RemoteResult? {
        val peliculaRemoto = moviesRemotoDataSource.getListPopularMovies(apikey)
        /*peliculaRemoto?.let {
            moviesLocalDataSource.saveMovies(peliculaRemoto.results)
        }*/
        return peliculaRemoto
    }
}