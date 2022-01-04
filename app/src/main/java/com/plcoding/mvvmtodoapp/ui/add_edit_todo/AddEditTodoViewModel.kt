package com.plcoding.mvvmtodoapp.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.mvvmtodoapp.data.Todo
import com.plcoding.mvvmtodoapp.data.TodoRepository
import com.plcoding.mvvmtodoapp.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    // tracks state of clicked Todoclass
    var todo by mutableStateOf<Todo?>(null)
        private set

    //tracks state of new title and desc
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        //check if we clicked on an existing todoObject or composing a new one

        //if we open a new todoObject then we need to load it from db

        //retrieve id from savedStateHandle
        savedStateHandle.get<Int>("todoId")
                ?.let {

                        id ->

                    //check if the id is equal to -1 (default id) or check if is existing
                    if (id != -1) {

                        //retrieve the todoItem from db

                        viewModelScope.launch {

                            repository.getTodoById(id)
                                    ?.let {
                                        todo = it
                                        title = it.title
                                        description = it.description ?: ""

                                    }

                        }
                    }

                }


    }


    fun onEvent(event: AddEditTodoEvent) {

        when (event) {
            is AddEditTodoEvent.OnTitleChange -> {

                title = event.title
            }
            is AddEditTodoEvent.OnDescriptionChange -> {

                description = event.description
            }
            is AddEditTodoEvent.OnSaveTodoClick -> {

                viewModelScope.launch {

                    //validate title

                    if (title.isBlank()) {

                        sendUIEvent(UIEvent.ShowSnackbar(message = "The title cannot be empty"))
                        //return control to launch start
                        return@launch
                    }

                    repository.insertTodo(Todo(
                        title = title,
                        description = description,
                        //if null set it to false
                        isDone = todo?.isDone ?: false
                    ))

                }
            }
        }


    }


    private fun sendUIEvent(event: UIEvent) {

        viewModelScope.launch {
            //send() needs to be done in Coroutine context
            _uiEvent.send(event)
        }
    }
}