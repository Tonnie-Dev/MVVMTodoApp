package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.lifecycle.ViewModel
import com.plcoding.mvvmtodoapp.data.TodoRepository
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoListEvent.*
import com.plcoding.mvvmtodoapp.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {


    // val todos = repository.getTodos()
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: TodoListEvent){

        when(event){

            is DeleteTodo ->{}
            is OnDoneChange ->{}
            is OnUndoneDeleteClick ->{}
            is OnTodoClick ->{}
            is OnAddTodoClick ->{}
        }
    }
}