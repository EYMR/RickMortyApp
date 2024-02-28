package com.eymr.rickandmorty.data.services

import com.eymr.rickandmorty.domain.models.RickMortyCharacters
import com.eymr.rickandmorty.domain.models.character.Data
import retrofit2.Response
import retrofit2.http.GET

interface IServicesAPI {
    @GET("api/character")
    suspend fun getCharacters():Response<Data>
}