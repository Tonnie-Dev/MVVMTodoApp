package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mvvmtodoapp.util.UIEvent

@Composable
fun TodoListScreen(onNavigate:(UIEvent.Navigate)-> Unit,
viewModel: TodoListViewModel = hiltViewModel()) {

}