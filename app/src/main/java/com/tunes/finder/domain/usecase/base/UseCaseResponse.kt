package com.tunes.finder.domain.usecase.base

import com.tunes.finder.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}