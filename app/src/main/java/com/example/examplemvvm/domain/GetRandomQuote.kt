package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.model.QuoteProvider
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuote @Inject constructor(
    private val quoteRepository: QuoteRepository,
) {

    suspend operator fun invoke(): Quote? {
        val quotes = quoteRepository.getAllQuotesFromDb()
        if (quotes.isEmpty()) return null

        return quotes[quotes.indices.random()]
    }
}