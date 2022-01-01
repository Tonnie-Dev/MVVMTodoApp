package com.plcoding.mvvmtodoapp.ui.todo_list

import com.plcoding.mvvmtodoapp.data.Todo

sealed class TodoListEvent {

    data class DeleteTodo(val todo: Todo) : TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodoListEvent()
    object OnUndoneDeleteClick : TodoListEvent()
    data class OnTodoClick(val todo: Todo) : TodoListEvent()
    object OnAddTodoClick : TodoListEvent()
}
