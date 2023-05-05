package com.example.catbook.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbook.data.local.CatBreedData
import com.example.catbook.repository.CatBookRepository
import com.example.catbook.utils.getCatBreedEpoxyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatHomeViewModel @Inject constructor(
    private val catBookRepository: CatBookRepository
) : ViewModel() {
    var currentPageNumber = 0
    var hasNextPage = true
    var isLoading = false
    val selectedCatBreedId = mutableListOf<String>()
    private val catBreedData = mutableListOf<CatBreedData>()
    private val _catBreedDataLiveData = MutableLiveData<List<CatBreedData>>()
    val catBreedDataLiveData: LiveData<List<CatBreedData>> by lazy { _catBreedDataLiveData }

    fun fetchCatBreedData() {
        if (!hasNextPage || isLoading) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = catBookRepository.fetchCatBreed(PAGE_LIMIT, currentPageNumber)
            if (response.errorBody() == null) {
                val newPageResults = response.body()
                if (newPageResults.isNullOrEmpty()) {
                    hasNextPage = false
                } else {
                    catBreedData.addAll(newPageResults.getCatBreedEpoxyData())
                    _catBreedDataLiveData.postValue(catBreedData)
                    currentPageNumber++
                }

            }
            isLoading = false
        }
    }


    companion object {
        const val PAGE_LIMIT = 10
    }
}