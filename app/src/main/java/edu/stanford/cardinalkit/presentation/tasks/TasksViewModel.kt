package edu.stanford.cardinalkit.presentation.tasks

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.stanford.cardinalkit.common.Constants
import edu.stanford.cardinalkit.domain.models.Response
import edu.stanford.cardinalkit.domain.models.tasks.CKTask
import edu.stanford.cardinalkit.domain.use_cases.tasks.TasksUseCases
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class TasksViewModel @Inject constructor(
    @Named(Constants.TASKS_USE_CASES)
    private var useCases: TasksUseCases
): ViewModel() {
    private val _tasksState = mutableStateOf<Response<List<CKTask>>>(Response.Loading)
    val tasksState: State<Response<List<CKTask>>> = _tasksState

    private val _currentDate = mutableStateOf<LocalDate>(LocalDate.now())
    val currentDate: State<LocalDate> = _currentDate

    init {
        getTasks()
    }

    fun getTasks() = viewModelScope.launch {
        useCases.getTasks().collect { response ->
            _tasksState.value = response
        }
    }

    fun setDate(date: LocalDate) {
        _currentDate.value = date
    }
}
