package com.eymr.rickandmorty.domain.repositories

import com.eymr.rickandmorty.core.utils.Resource
import com.eymr.rickandmorty.domain.models.RickMortyCharacters
import kotlinx.coroutines.flow.Flow


interface IRickMortyRepository {
    suspend fun getCharacters(): Flow<Resource<List<RickMortyCharacters>>>
}