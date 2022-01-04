package com.plcoding.mvvmtodoapp.ui.add_edit_todo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.plcoding.mvvmtodoapp.data.Todo
import com.plcoding.mvvmtodoapp.data.TodoRepository
import com.plcoding.mvvmtodoapp.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {

    var todo = mutableStateOf<Todo?>(null)
    private set

    var title = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
}