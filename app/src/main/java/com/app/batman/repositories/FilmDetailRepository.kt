package com.app.batman.repositories

import com.app.batman.core.Const
import com.app.batman.core.datamodel.DataState
import com.app.batman.database.FilmsDao
import com.app.batman.models.Film
import com.app.batman.services.FilmsWebServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmDetailRepository @Inject constructor(
    private val filmsWebService: FilmsWebServices,
    private val filmsDao: FilmsDao
) {
    suspend fun filmDetails(imdbId: String): Flow<DataState<Film?>> = flow {
        emit(DataState.Loading)
        try {
            val response = filmsWebService.getFilmDetail(
                Const.apiKey, imdbId
            )
            if (response.isSuccessful) {
                response.body()?.let { film ->
                    emit(DataState.Success(film))
                    filmsDao.updateFilm(film)
                }
            } else
                emit(DataState.RetrofitError(response.errorBody()?.string()))
        } catch (e: Exception) {
            emit(DataState.Success(filmsDao.filmDetails(imdbId)))
            emit(DataState.Error(e))
        }
    }
}