package com.example.data.api.services.characters

data class ApiCharactersData(val limit: Int,
                             val total: Int,
                             val count: Int,
                             val results: List<ApiCharacter>)