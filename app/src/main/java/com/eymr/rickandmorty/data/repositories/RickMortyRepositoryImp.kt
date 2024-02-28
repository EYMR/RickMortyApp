package com.eymr.rickandmorty.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.eymr.rickandmorty.core.utils.Resource
import com.eymr.rickandmorty.core.utils.toDomain
import com.eymr.rickandmorty.data.services.IServicesAPI
import com.eymr.rickandmorty.domain.models.RickMortyCharacters
import com.eymr.rickandmorty.domain.repositories.IRickMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RickMortyRepositoryImp @Inject constructor(
    private val services: IServicesAPI
) : IRickMortyRepository {

    override suspend fun getCharacters(): Flow<Resource<List<RickMortyCharacters>>> = flow {
        while (true) {
            try {
                val response = services.getCharacters()
                if (response.isSuccessful) {
                    val body = response.body()?.results
                    val newList = body?.map { result ->
                        result.toDomain()

                    }
                    if (body != null) {
                        emit(Resource.Success(newList ?: emptyList()))
                    } else {
                        emit(Resource.Failure("Error de parsing"))
                    }
                } else {
                    emit(Resource.Failure("Error en la llamada a la API"))
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e.message))
            }
        }
    }
}
