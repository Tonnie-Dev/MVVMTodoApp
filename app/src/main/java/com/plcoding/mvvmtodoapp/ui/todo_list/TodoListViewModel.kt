package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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



    private fun sendUIEvent(event: UIEvent){

        viewModelScope.launch {
            //send() needs to be done in Coroutine context
            _uiEvent.send(UIEvent.Navigate(Routes.ADD_EDIT_LIST))
        }
    }
}