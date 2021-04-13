package com.example.data.repositories.characters

import com.example.data.api.CoroutinesTestRule
import com.example.data.api.FakeMarvelDataSource
import com.example.data.api.FakeRoomDataSource
import com.example.data.api.MarvelDataSource
import com.example.data.api.services.characterdetails.CharacterDetailsService
import com.example.data.api.services.characters.*
import com.example.data.local.RoomDataSource
import com.example.data.local.dao.MarvelDatabase
import com.example.data.toDomain
import com.example.domain.domain.model.characterdetails.CharacterDetails
import com.example.domain.domain.model.characterdetails.ThumbnailCharacterDetails
import com.example.domain.domain.model.characters.Character
import com.example.domain.domain.model.characters.ThumbnailCharacter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersRepositoryImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

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

    @Test(expected = TimeoutCancellationException::class)
    fun `After timeout, an exception is thrown`() = runBlockingTest {

        repositoryImpl.checkRequiredNewPage(0)
        advanceTimeBy(5_000)
    }

    @Test
    fun `when database is not empty, a list is returned`() = runBlockingTest {

        fakeRoomDataSource.saveCharacters(fakeCharacters)
        repositoryImpl.getCharacters().collect {
            Assert.assertEquals(fakeCharacters, it)
        }
    }
}