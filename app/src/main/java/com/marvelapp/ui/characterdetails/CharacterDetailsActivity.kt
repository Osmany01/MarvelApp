package com.marvelapp.ui.characterdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.marvelapp.databinding.ActivityCharacterDetailBinding
import com.marvelapp.ui.common.IMAGE_QUALITY
import com.marvelapp.ui.common.collectFlow
import com.marvelapp.ui.common.loadWithGlide
import dagger.android.AndroidInjection
import javax.inject.Inject

class CharacterDetailsActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CharacterDetailsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityCharacterDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCharacterDetails(intent.getIntExtra(ARG_CHARACTERID, 0))
        lifecycleScope.collectFlow(viewModel.characterDetailsUiState) { characterDetailsUiState ->
            when(characterDetailsUiState) {
                is CharacterDetailsViewModel.CharacterDetailsUiState.Success -> setUpViews(characterDetailsUiState.characterDetails)
                is CharacterDetailsViewModel.CharacterDetailsUiState.Error -> setUpError(characterDetailsUiState.message)
            }
        }

    }

    private fun setUpError(message: String) {

        binding.characterImage.visibility = View.GONE
        binding.characterTitle.visibility = View.GONE
        binding.characterDescription.visibility = View.GONE

        binding.error.text = message
        binding.error.visibility = View.VISIBLE
    }

    private fun setUpViews(characterDetails: CharacterDetails) {

        binding.error.visibility = View.GONE

        binding.characterImage.loadWithGlide(characterDetails.thumbnail?.path?.plus(IMAGE_QUALITY).plus(characterDetails.thumbnail?.extension))
        binding.characterTitle.text = characterDetails.name
        binding.characterDescription.text = characterDetails.description

        binding.characterImage.visibility = View.VISIBLE
        binding.characterTitle.visibility = View.VISIBLE
        binding.characterDescription.visibility = View.VISIBLE
    }

    companion object {

        private const val ARG_CHARACTERID = "ARG_CHARACTERID"
        fun launchIntent(context: Context, characterId: Int) =
            Intent(context, CharacterDetailsActivity::class.java)
                .apply {
                    putExtra(ARG_CHARACTERID, characterId)
                }
    }
}
