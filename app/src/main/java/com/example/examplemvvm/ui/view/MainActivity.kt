package com.example.examplemvvm.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.examplemvvm.databinding.ActivityMainBinding
import com.example.examplemvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quoteViewModel : QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this) { quote ->
            binding.tvQuote.text = quote.quote
            binding.tvAuthor.text = "- ${quote.author}"
        }

        quoteViewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }

        binding.viewContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }
}