package com.plcoding.mvvmtodoapp.ui.add_edit_todo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.plcoding.mvvmtodoapp.data.Todo
import com.plcoding.mvvmtodoapp.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(private val repository: TodoRepository) :
    ViewModel() {

    var todo: Todo? = mutableStateOf(null)
        private set

    var title = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set
}