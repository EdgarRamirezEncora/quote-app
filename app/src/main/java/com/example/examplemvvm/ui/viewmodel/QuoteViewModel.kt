package com.example.examplemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.domain.GetQuotes
import com.example.examplemvvm.domain.GetRandomQuote
import com.example.examplemvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotes: GetQuotes,
    private val getRandomQuote: GetRandomQuote
) : ViewModel() {
    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotes()

            if (result.isNotEmpty()) {
                isLoading.postValue(false)
                quoteModel.postValue(result[0])
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuote()

            quoteModel.postValue(
                quote ?:
                Quote("Default quote", "Default author")
            )

            isLoading.postValue((false))
        }
    }
}