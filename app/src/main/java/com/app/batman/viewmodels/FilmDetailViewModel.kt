package com.app.batman.viewmodels

//import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.batman.core.datamodel.DataState
import com.app.batman.models.Film
import com.app.batman.repositories.FilmDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val filmDetailRepository: FilmDetailRepository
) : ViewModel() {

    private val _filmDetailsStateFlow = MutableSharedFlow<DataState<Film?>>()
    val filmDetailsStateFlow = _filmDetailsStateFlow.asSharedFlow()

    fun setEvent(filmDetailsEvent: FilmDetailsEvent) {
        when (filmDetailsEvent) {
            is FilmDetailsEvent.ShowFilmDetailsEvent -> {
                viewModelScope.launch {
                    filmDetailRepository.filmDetails(filmDetailsEvent.imdbId).collect {
                        _filmDetailsStateFlow.emit(it)
                    }
                }
            }
        }
    }

}

sealed class FilmDetailsEvent {
    class ShowFilmDetailsEvent(val imdbId: String) : FilmDetailsEvent()
}