package com.plcoding.mvvmtodoapp.ui

import com.plcoding.mvvmtodoapp.util.Routes

sealed class Screens(val route:String){

    object TodoListScreen: Screens(Routes.TODO_LIST)
    object AddEditTodoScreen:Screens(Routes.ADD_EDIT_TODO)
}
