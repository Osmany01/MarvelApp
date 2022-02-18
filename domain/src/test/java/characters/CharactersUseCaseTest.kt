package characters

import com.example.domain.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import com.example.domain.domain.usecase.characters.GetCharactersUseCase
import com.nhaarman.mockito_kotlin.mock
import common.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import model.aCharacterDetails
import model.aCharacterList
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class CharactersUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val getCharactersUseCase: GetCharactersUseCase = mock()

    @Test
    fun `should return a character detail`() = runBlockingTest {

        val expected = aCharacterList()
        val flow = flow {
            emit(aCharacterList())
        }
        Mockito.`when`(getCharactersUseCase.getCharacters()).thenReturn(flow)
        val result = getCharactersUseCase.getCharacters()

        result.collect {
            Assert.assertEquals(expected, it)
        }
    }
}