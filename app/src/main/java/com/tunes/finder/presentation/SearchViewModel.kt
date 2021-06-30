package com.tunes.finder.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunes.finder.domain.model.ApiError
import com.tunes.finder.domain.model.Media
import com.tunes.finder.domain.usecase.GetOpenTimeUseCase
import com.tunes.finder.domain.usecase.LookupUseCase
import com.tunes.finder.domain.usecase.SearchUseCase
import com.tunes.finder.domain.usecase.SetOpenTimeUseCase
import com.tunes.finder.domain.usecase.base.UseCaseResponse
import com.tunes.finder.utils.TimeUtil
import kotlinx.coroutines.cancel

class SearchViewModel constructor(
    private val searchUseCase: SearchUseCase,
    private val lookupUseCase: LookupUseCase,
    private val getOpenTimeUseCase: GetOpenTimeUseCase,
    private val setOpenTimeUseCase: SetOpenTimeUseCase
) : ViewModel() {

    val showProgressBar = MutableLiveData<Boolean>()
    val searchResultData = MutableLiveData<Media>()
    val messageData = MutableLiveData<String>()

    val openTimeData = MutableLiveData<String>()

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

    fun lookup(id: Long) {
        showProgressBar.value = true
        lookupUseCase.invoke(viewModelScope, id, object : UseCaseResponse<Media> {
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

    fun getOpenTime() {
        getOpenTimeUseCase.invoke(viewModelScope, null, object : UseCaseResponse<String> {
            override fun onSuccess(result: String) {
                openTimeData.value = result
            }

            override fun onError(apiError: ApiError?) {

            }
        })
    }

    fun setOpenTime() {
        setOpenTimeUseCase.invoke(viewModelScope, TimeUtil.current(), object : UseCaseResponse<Unit> {
            override fun onSuccess(result: Unit) {

            }

            override fun onError(apiError: ApiError?) {

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