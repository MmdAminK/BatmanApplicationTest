package com.app.batman.repositories

import com.app.batman.core.Const
import com.app.batman.core.datamodel.DataState
import com.app.batman.database.FilmsDao
import com.app.batman.models.Film
import com.app.batman.services.FilmsWebServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val filmsWebService: FilmsWebServices,
    private val filmsDao: FilmsDao
) {

    suspend fun films(): Flow<DataState<List<Film>?>> = flow {
        emit(DataState.Loading)
        try {
            val response = filmsWebService.getFilms(
                Const.apiKey, Const.apiSValue
            )
            if (response.isSuccessful) {
                emit(DataState.Success(response.body()?.films))
                filmsDao.insertFilms(response.body()?.films!!)
            } else {
                emit(DataState.RetrofitError(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            emit(DataState.Success(filmsDao.films()))
            emit(DataState.Error(e))
        }
    }
}