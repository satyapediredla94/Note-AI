package com.example.noteaiagent.domain

sealed interface Resource {
    data class Success<T>(val data: T) : Resource
    data class Error(val message: String) : Resource
    data object Loading : Resource
}