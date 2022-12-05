package aguinaga.armando.mymovietvapp.domain.usecases

import aguinaga.armando.mymovietvapp.data.model.RemoteResult
import aguinaga.armando.mymovietvapp.data.sources.repositories.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(
        forzarActualizacion: Boolean,
        apikey: String
    ): RemoteResult? {
        moviesRepository.forzarActualizacion = forzarActualizacion
        return moviesRepository.getListPopularMovies(apikey)
    }
}