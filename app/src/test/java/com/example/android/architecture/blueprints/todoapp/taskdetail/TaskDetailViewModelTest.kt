package com.example.android.architecture.blueprints.todoapp.taskdetail

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TaskDetailViewModelTest {
    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var taskDetailViewModel: TaskDetailViewModel

    @Before
    fun setupViewModel() {
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        taskDetailViewModel = TaskDetailViewModel(tasksRepository)
    }

    @Test
    fun setCompleteForFirstElement_NoReturn_HaveCompleteToTrue() {

    }
}