package com.plcoding.mvvmtodoapp.ui.add_edit_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mvvmtodoapp.util.UIEvent
import kotlinx.coroutines.flow.collect


/*onPopBackStack handles the popBackStack event when received*/
@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {

    //Scaffold state for showing the snackbar
    val scaffoldState = rememberScaffoldState()


    //Launched Effect for handling events
    LaunchedEffect(key1 = true) {

        //use viewModel to collect event
        viewModel.uiEvent.collect { event ->
            when (event) {

                is UIEvent.PopBackStack -> {

                    //propagate state

                    onPopBackStack()

                }
                is UIEvent.ShowSnackbar -> {

                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message,
                        actionLabel = event.action
                    )

                }
                else -> Unit
            }
        }

    }


    Scaffold(scaffoldState = scaffoldState, floatingActionButton = {

        FloatingActionButton(onClick = {
            //fire event from viewModel
            viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick)

        }) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "Save")

        }
    }) {

        Column(modifier = Modifier.fillMaxWidth()) {

            TextField(
                value = viewModel.title,
                onValueChange = {
                    viewModel.onEvent(AddEditTodoEvent.OnTitleChange(it))
                },
                label = {
                        Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth())
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = viewModel.description,
            onValueChange = {

                viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(it))
            },

            label = {
                Text(text = "Description")
            },
            singleLine = false,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )
    }


}