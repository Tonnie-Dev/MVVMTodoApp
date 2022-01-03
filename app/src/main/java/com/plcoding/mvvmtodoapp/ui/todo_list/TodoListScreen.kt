package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mvvmtodoapp.util.UIEvent
import kotlinx.coroutines.flow.collect

@Composable
fun TodoListScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {

    /*viewModel.todos returns a flow but we need this as compose state
    * we use flow.collectAsState(), we pass an empty list for initial value*/
    val todos by viewModel.todos.collectAsState(initial = emptyList())

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

        //this will now be triggered for every single event sent to UI channel
        viewModel.uiEvent.collect { event ->

            when (event) {

                is UIEvent.ShowSnackbar -> {

                    //establish if an action was performed using result

                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )


                    if (result === SnackbarResult.ActionPerformed) {

                        //send undo delete event to viewModel when snackbar is clicked
                        viewModel.onEvent(TodoListEvent.OnUndoneDeleteClick)
                    }

                }

                is UIEvent.Navigate -> {

                    onNavigate(event)
                }

                //we don't want to pop back stack in TodoListScreen
                else -> Unit
            }


        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {


            FloatingActionButton(onClick = { viewModel.onEvent(TodoListEvent.OnAddTodoClick) }) {

                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {


        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(todos) { todo ->

                TodoItem(
                    todo = todo,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.onEvent(TodoListEvent.OnTodoClick(todo = todo))})
            }
        }

    }

}