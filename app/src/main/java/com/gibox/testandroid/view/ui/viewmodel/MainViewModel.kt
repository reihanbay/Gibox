/*
 * Created by Muhamad Syafii
 * , 5/4/2022
 * Copyright (c) 2022 by Gibox Digital Asia.
 * All Rights Reserve
 */

package com.gibox.testandroid.view.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gibox.testandroid.core.ApiResponse
import com.gibox.testandroid.core.data.auth.source.remote.request.LoginRequest
import com.gibox.testandroid.core.data.auth.source.remote.response.DataItem
import com.gibox.testandroid.core.data.vo.Resource
import com.gibox.testandroid.core.domain.auth.model.LoginEntityDomain
import com.gibox.testandroid.core.domain.auth.usecase.AuthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _isErrorRequestLogin = MutableLiveData<String>()
    private val _dataRequestLogin = MutableLiveData<LoginEntityDomain>()
    private val _isLoadingRequestLogin = MutableLiveData<Boolean>()

    private val _isErrorRequestList = MutableLiveData<String>()
    private val _dataRequestList = MutableLiveData<DataItem>()
    private val _isLoadingRequestList = MutableLiveData<Boolean>()

    var isErrorRequestLogin = _isErrorRequestLogin
    var dataRequestLogin = _dataRequestLogin
    var isLoadingRequestLogin = _isLoadingRequestLogin

    var isErrorRequestList = _isErrorRequestList
    var dataRequestList = _dataRequestList
    var isLoadingRequestList = _isLoadingRequestList

    fun requestLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            isLoadingRequestLogin.value = true
            withContext(Dispatchers.IO) {
                authUseCase.doLogin(loginRequest).collect {
                    when (it) {
                        is Resource.Success -> {
                            dataRequestLogin.postValue(it.data)
                        }
                        is Resource.Loading -> {
                            isLoadingRequestLogin.postValue(true)
                        }
                        is Resource.Error -> {
                            isErrorRequestLogin.postValue(it.message)
                        }
                    }
                }
            }
        }
    }

    fun requestListUser() {
        viewModelScope.launch {
            isLoadingRequestList.value = true
            withContext(Dispatchers.IO) {
                authUseCase.getUserList().collect {
                    it.map {
                    }
                }
            }
        }
    }

}