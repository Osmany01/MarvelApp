package com.marvelapp.characters

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marvelapp.R
import com.marvelapp.ui.characters.CharactersActivity
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharactersActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<CharactersActivity>()

    @Before
    fun setUp() {
        val scenario = launchActivity<CharactersActivity>()
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun when_TheActivityStart_RecyclerView_Displayed() {

        assertDisplayed(R.id.recycler)
    }

    @Test
    fun when_TheActivityStart_ProgressBar_Displayed() {

        assertNotDisplayed(R.id.progressBar)
    }
}