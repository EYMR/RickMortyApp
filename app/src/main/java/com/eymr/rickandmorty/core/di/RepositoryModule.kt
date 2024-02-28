package com.eymr.rickandmorty.core.di

import com.eymr.rickandmorty.data.repositories.RickMortyRepositoryImp
import com.eymr.rickandmorty.domain.repositories.IRickMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for providing repository implementations using Dagger-Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRickMortyRepository(repository: RickMortyRepositoryImp): IRickMortyRepository
}
