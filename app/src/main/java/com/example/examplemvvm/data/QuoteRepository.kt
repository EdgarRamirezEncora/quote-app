package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.model.QuoteProvider
import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.Quote
import com.example.examplemvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteService: QuoteService,
    private val quoteDao: QuoteDao

) {

    suspend fun getAllQuoteFromApi(): List<Quote> {
        return quoteService.getQuotes().map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDb(): List<Quote> {
        return quoteDao.getAllQuotes().map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun  clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}