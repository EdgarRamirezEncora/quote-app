package com.example.examplemvvm.domain

import com.example.examplemvvm.data.QuoteRepository
import com.example.examplemvvm.data.database.entities.toDatabase
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject


class GetQuotes @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend operator fun invoke(): List<Quote> {
        val quotes = quoteRepository.getAllQuoteFromApi()

        return if (quotes.isNotEmpty()) {
            quoteRepository.clearQuotes()
            quoteRepository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            quoteRepository.getAllQuotesFromDb()
        }
    }
}