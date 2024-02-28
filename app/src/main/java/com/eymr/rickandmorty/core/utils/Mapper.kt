package com.eymr.rickandmorty.core.utils

import com.eymr.rickandmorty.domain.models.Location
import com.eymr.rickandmorty.domain.models.Origin
import com.eymr.rickandmorty.domain.models.RickMortyCharacters
import com.eymr.rickandmorty.domain.models.character.Result

fun Result.toDomain(): RickMortyCharacters {
    return RickMortyCharacters(

        created = this.created,
        gender = this.gender,
        id = this.id,
        image = this.image,
        name = this.name,
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url
    )
}