package com.example.nbaanalytics.network.base

sealed class Error {
    data object NetworkError : Error()
    data object GenericError : Error()
    data object ResponseError : Error()
    data object PersistenceError : Error()
}