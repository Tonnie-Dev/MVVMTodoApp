package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mvvmtodoapp.util.UIEvent

@Composable
fun TodoListScreen(onNavigate:(UIEvent.Navigate)-> Unit,
viewModel: TodoListViewModel = hiltViewModel()) {

    /*viewModel.todos returns a flow but we need this as compose state
    * we use flow.collectAsState()*/
    val todos = viewModel.todos

    LaunchedEffect(key1 = true){

        
    }

}