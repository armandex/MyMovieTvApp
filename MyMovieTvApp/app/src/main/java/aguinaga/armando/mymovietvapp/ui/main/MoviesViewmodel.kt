package aguinaga.armando.mymovietvapp.ui.main

import aguinaga.armando.mymovietvapp.data.model.RemoteResult
import aguinaga.armando.mymovietvapp.domain.usecases.GetMoviesUseCase
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewmodel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    //private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

    private val _receiveMovies = MutableLiveData<RemoteResult?>()
    private val _mostrarProgress = MutableLiveData<Boolean>()

    val getMovies: LiveData<RemoteResult?>
        get() = _receiveMovies

    val mostrarProgress: LiveData<Boolean>
        get() = _mostrarProgress

    /*val userItemsUiStates = userRepositoryImpl.getUsers()
        .map { pagingData ->
            pagingData.map { userModel -> UserItemUiState(userModel) }
        }.cachedIn(viewModelScope)*/

    fun obtenerMovies(forzarActualizacion: Boolean, apikey: String) = viewModelScope.launch {
        _mostrarProgress.value = true
        _receiveMovies.value = getMoviesUseCase.invoke(forzarActualizacion, apikey)
        _mostrarProgress.value = false
    }

}