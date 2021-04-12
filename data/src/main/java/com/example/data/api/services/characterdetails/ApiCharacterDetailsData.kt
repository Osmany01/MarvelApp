package com.example.data.api.services.characterdetails

data class ApiCharacterDetailsData(
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ApiCharacterDetails
)