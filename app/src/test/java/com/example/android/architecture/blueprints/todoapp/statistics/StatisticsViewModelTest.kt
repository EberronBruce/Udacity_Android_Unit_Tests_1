package com.example.android.architecture.blueprints.todoapp.statistics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.architecture.blueprints.todoapp.MainCoroutineRule
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StatisticsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Subject under test
    private lateinit var statisticsViewModel: StatisticsViewModel

    // Use a fake repository to be injected into the view model.
    private lateinit var taskRepository: FakeTestRepository

    @Before
    fun setupStatisticsViewModel() {
        // Initialise the repository with no tasks.
        taskRepository = FakeTestRepository()

        statisticsViewModel = StatisticsViewModel(taskRepository)
    }

    @Test
    fun loadTasks_loading() {
        // Pause dispatcher so you can verify initial values
        mainCoroutineRule.pauseDispatcher()

        //Load the task in the view model
        statisticsViewModel.refresh()

        // Then assert that the progress indicator is shown
        assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(true))

        // Execute pending coroutines actions
        mainCoroutineRule.resumeDispatcher()

        // Then assert that the progress indicator is hidden
        assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(false))
    }

    @Test
    fun loadStatisticsWhenTaskAreUnavailable_callErrorToDisplay() {
        // Make the repository return errors
        taskRepository.setReturnError(true)
        statisticsViewModel.refresh()

        // Then empty and error are true (which triggers and error message to be shown)
        assertThat(statisticsViewModel.empty.getOrAwaitValue(), `is`(true))
        assertThat(statisticsViewModel.error.getOrAwaitValue(), `is`(true))
    }
}