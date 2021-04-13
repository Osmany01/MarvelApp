package com.marvelapp.ui.characters


import com.example.data.repositories.characters.CharactersRepositoryImpl
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characterdetails.ThumbnailCharacterDetails
import com.example.domain.domain.model.characters.Character
import com.example.domain.domain.model.characters.ThumbnailCharacter
import com.example.domain.domain.usecase.characters.GetCharactersUseCase
import com.marvelapp.common.CoroutinesUiTestRule
import com.marvelapp.common.FakeMarvelDataSource
import com.marvelapp.common.FakeRoomDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.internal.wait
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesUiTestRule()

    private val fakeThanosThumbnail = ThumbnailCharacter("Thanospaht", "jpeg")
    private val fakePantherThumbnail = ThumbnailCharacter("Pantherpath", "jpeg")
    private val fakeIronManThumbnail = ThumbnailCharacter("IronManpath", "jpeg")

    private val fakeCharacters = listOf(
        Character(1001, "Thanos", "this is thano", fakeThanosThumbnail),
        Character(1002, "Panther", "this is Panther", fakePantherThumbnail),
        Character(1003, "IronMan", "this is IronMan", fakeIronManThumbnail),
    )

    private val fakeThanosDetailsThumbnail = ThumbnailCharacterDetails("Thanospaht", "jpeg")

    private val fakeCharacterDetails = CharacterDetails(1001, "Thanos", "this is thano", fakeThanosDetailsThumbnail)

    private val fakeRoomDataSource = FakeRoomDataSource()
    private val fakeMarvelDataSource = FakeMarvelDataSource(fakeCharacters, fakeCharacterDetails, 6_000)
    private val repositoryImpl = CharactersRepositoryImpl(fakeRoomDataSource, fakeMarvelDataSource)
    private val getCharactersUseCase = GetCharactersUseCase(repositoryImpl)


    @Test
    fun `listening to characters flow emits the list of characters from server`() = runBlockingTest {
        fakeRoomDataSource.saveCharacters(fakeCharacters)
        val viewModel = CharactersViewModel(getCharactersUseCase)


        viewModel.characters.collect {
            Assert.assertEquals(fakeCharacters, it)
        }
    }
}