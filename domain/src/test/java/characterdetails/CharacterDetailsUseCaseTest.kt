package characterdetails

import com.example.domain.domain.usecase.characterdetails.GetCharacterDetailsUseCase
import com.nhaarman.mockito_kotlin.mock
import common.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import model.aCharacterDetails
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class CharacterDetailsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase = mock()

    @Test
    fun `should return a character detail`() = runBlockingTest {

        val expected = aCharacterDetails()
        val flow = flow {
            emit(aCharacterDetails())
        }
        Mockito.`when`(getCharacterDetailsUseCase.checkCharacterDetails(0)).thenReturn(flow)
        val result = getCharacterDetailsUseCase.checkCharacterDetails(0)

        result.collect {
            Assert.assertEquals(expected, it)
        }
    }
}