package com.app.batman.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.batman.core.datamodel.DataState
import com.app.batman.models.Film
import com.app.batman.repositories.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val filmsRepository: FilmRepository
) : ViewModel() {
    var page: Int = 0
    private val _filmsSharedFlow = MutableSharedFlow<DataState<List<Film>?>>()
    val filmsSharedFlow = _filmsSharedFlow.asSharedFlow()

    fun setEvent(filmsEvent: FilmsEvent) {
        when (filmsEvent) {
            is FilmsEvent.ShowFilmsEvent -> {
                viewModelScope.launch {
                    filmsRepository.films().collect {
                        _filmsSharedFlow.emit(it)
                    }

                }

            }
        }
    }


}

sealed class FilmsEvent {
    object ShowFilmsEvent : FilmsEvent()
}