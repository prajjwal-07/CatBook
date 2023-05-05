package com.example.catbook.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbook.data.local.CatImageData
import com.example.catbook.repository.CatBookRepository
import com.example.catbook.utils.getCatImageEpoxyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatImageViewModel @Inject constructor(
    private val catBookRepository: CatBookRepository
) : ViewModel() {

    var breedIdList: Array<String>? = null
    var currentPageNumber = 0
    var hasNextPage = true
    var isLoading = false
    private val catImageData = mutableListOf<CatImageData>()
    private val _catImageDataLiveData = MutableLiveData<List<CatImageData>>()
    val catImageDataLiveData: LiveData<List<CatImageData>> by lazy { _catImageDataLiveData }

    fun fetchCatImageData() {
        if (!hasNextPage || isLoading) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = catBookRepository.fetchCatImage(
                PAGE_LIMIT,
                currentPageNumber,
                breedIdList?.joinToString(",").orEmpty()
            )
            if (response.errorBody() == null) {
                val newPageResults = response.body()
                if (newPageResults.isNullOrEmpty()) {
                    hasNextPage = false
                } else {
                    catImageData.addAll(newPageResults.getCatImageEpoxyData())
                    _catImageDataLiveData.postValue(catImageData)
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