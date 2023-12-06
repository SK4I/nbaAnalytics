package com.example.nbaanalytics.network.base

sealed class Result<out T : Any, out R> {
    class Success<out T : Any>(val successData: T) : Result<T, Nothing>()
    class Failure<out R : Error>(val errorData: R) : Result<Nothing, R>()

    fun handleResult(
        successBlock: (T) -> Unit = {},
        failureBlock: (R) -> Unit = {},
    ) {
        when (this) {
            is Success -> successBlock(successData)
            is Failure -> failureBlock(errorData)
        }
    }
}