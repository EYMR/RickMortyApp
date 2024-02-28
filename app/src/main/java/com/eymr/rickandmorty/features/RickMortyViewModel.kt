package com.eymr.rickandmorty.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eymr.rickandmorty.core.di.IoDispatcher
import com.eymr.rickandmorty.core.utils.Resource
import com.eymr.rickandmorty.domain.models.RickMortyCharacters
import com.eymr.rickandmorty.domain.repositories.IRickMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(
    private val rickMortyRepository: IRickMortyRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _listCharacters = MutableStateFlow<List<RickMortyCharacters>>(emptyList())


    val uiState: StateFlow<List<RickMortyCharacters>>
        get() = _listCharacters.asStateFlow()

    init {
        fetchRickMortyCharacters()
    }

    fun fetchRickMortyCharacters() {
        viewModelScope.launch(dispatcher) {
            rickMortyRepository.getCharacters().collect { response ->
                when (response) {
                    is Resource.Failure -> {

                        _listCharacters.update { emptyList() }
                    }

                    is Resource.Success -> {

                        _listCharacters.update { response.data }
                    }

                }
            }

        }
    }
}


