package com.example.weatherapp.core.use_case

import arrow.core.Either
import com.example.weatherapp.core.failure.AppFailure

interface UseCase<out T, in P> {
    suspend operator fun invoke(params: P): Either<AppFailure, T>
}