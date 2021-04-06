package com.marvelapp.ui.characters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.marvelapp.databinding.ActivityCharactersBinding
import com.marvelapp.ui.common.collectFlow
import com.marvelapp.ui.common.lastVisibleEvents
import com.marvelapp.ui.common.visible
import dagger.android.AndroidInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CharactersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CharactersViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val charactersAdapter = CharactersAdapter(lifecycleScope)

        lifecycleScope.collectFlow(viewModel.spinner) { binding.progress.visible = it }
        lifecycleScope.collectFlow(viewModel.characters) { charactersAdapter.submitList(it) }

        lifecycleScope.collectFlow(binding.recycler.lastVisibleEvents) { viewModel.notifyLastVisible(it) }

        binding.recycler.adapter = charactersAdapter
    }
}