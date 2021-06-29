package com.tunes.finder.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunes.finder.domain.model.ApiError
import com.tunes.finder.domain.model.Media
import com.tunes.finder.domain.usecase.SearchUseCase
import com.tunes.finder.domain.usecase.base.UseCaseResponse
import kotlinx.coroutines.cancel

class SearchViewModel constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    val showProgressBar = MutableLiveData<Boolean>()
    val searchResultData = MutableLiveData<Media>()
    val messageData = MutableLiveData<String>()

    fun search(term: String) {
        showProgressBar.value = true
        searchUseCase.invoke(viewModelScope, term, object : UseCaseResponse<Media> {
            override fun onSuccess(result: Media) {
                searchResultData.value = result
                showProgressBar.value = false
            }

            override fun onError(apiError: ApiError?) {
                messageData.value = apiError?.getErrorMessage()
                showProgressBar.value = false
            }
        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = SearchViewModel::class.java.name
    }

}