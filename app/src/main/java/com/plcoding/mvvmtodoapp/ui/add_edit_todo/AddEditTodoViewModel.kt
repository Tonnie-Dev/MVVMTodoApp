package com.plcoding.mvvmtodoapp.ui.add_edit_todo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.plcoding.mvvmtodoapp.data.Todo
import com.plcoding.mvvmtodoapp.data.TodoRepository
import com.plcoding.mvvmtodoapp.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(private val repository: TodoRepository,
                                               val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    // tracks state of clicked Todoclass
    var todo = mutableStateOf<Todo?>(null)
    private set

    //tracks state of new title and desc
    var title = mutableStateOf("")
        private set
    var description = mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        //check if we clicked on an existing todoObject or composing a new one

        //if we open a new todoObject then we need to load it from db

        //retrieve id from savedStateHandle
       val id = savedStateHandle.get<Int>("todoId")
    }
}