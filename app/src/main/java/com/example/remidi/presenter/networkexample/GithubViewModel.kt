package com.example.remidi.presenter.networkexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remidi.data.DataSearchByRepo
import com.example.remidi.data.DataUserSearchByName
import com.example.remidi.network.GithubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.remidi.commons.Result

class GithubViewModel (
    private val githubRepo: GithubRepo
) : ViewModel() {
    private var dataUserByName = MutableLiveData(DataUserSearchByName())
    private var dataSearchByRepo = MutableLiveData(DataSearchByRepo())

    var isEmptyCase = MutableLiveData(false)

    var isFailureCaseMessage = MutableLiveData("")

    var isLoadingCase = MutableLiveData(false)

    fun getUserByNameData(): MutableLiveData<DataUserSearchByName> {
        return dataUserByName
    }
    fun getSearchByRepo(): MutableLiveData<DataSearchByRepo> {
        return dataSearchByRepo
    }

    fun onLoadData(keyword: String) {
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = githubRepo.getListUser(keyword)
            when (response) {
                is Result.Success -> {
                    dataUserByName.postValue(response.value)
                }
                is Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
        isLoadingCase.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = githubRepo.getUserRepo(keyword)
            when (response) {
                is Result.Success -> {
                    dataSearchByRepo.postValue(response.value)
                }
                is Result.Error -> {
                    isFailureCaseMessage.postValue(response.toString())
                }
            }
        }
    }
}