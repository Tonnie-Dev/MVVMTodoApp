package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.mvvmtodoapp.data.Todo
import com.plcoding.mvvmtodoapp.data.TodoRepository
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoListEvent.*
import com.plcoding.mvvmtodoapp.util.Routes
import com.plcoding.mvvmtodoapp.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {


    val todos = repository.getTodos()
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    //var to track the deleted todo
    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {

        when (event) {

            is DeleteTodo -> {

                viewModelScope.launch {
                    deletedTodo = event.todo


                    repository.deleteTodo(event.todo)

                    //send UI event to show snackbar
                    sendUIEvent(
                        UIEvent.ShowSnackbar(
                            message = "Todo Deleted",
                            action = "Undo"
                        )
                    )
                }
            }

            is OnDoneChange -> {

                viewModelScope.launch {

                    repository.insertTodo(event.todo.copy(isDone = event.isDone))
                }
            }

            is OnUndoneDeleteClick -> {
                deletedTodo?.let {
                        todo ->
                    viewModelScope.launch {

                        repository.insertTodo(todo = todo)
                    }

                }

            }
            is OnTodoClick -> {

                sendUIEvent(UIEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is OnAddTodoClick -> {
                sendUIEvent(UIEvent.Navigate(Routes.ADD_EDIT_TODO))

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