package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mvvmtodoapp.util.UIEvent
import kotlinx.coroutines.flow.collect

@Composable
fun TodoListScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {

    /*viewModel.todos returns a flow but we need this as compose state
    * we use flow.collectAsState()*/
    val todos = viewModel.todos

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

        //this will now be triggered for every single event sent to UI channel
        viewModel.uiEvent.collect { event ->

            when (event) {

                is UIEvent.ShowSnackbar -> {

                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )

                }
                is UIEvent.Navigate -> {

                    onNavigate(event)
                }

                //we don't want to pop back stack in TodoListScreen
                else -> Unit
            }


        }
    }

}