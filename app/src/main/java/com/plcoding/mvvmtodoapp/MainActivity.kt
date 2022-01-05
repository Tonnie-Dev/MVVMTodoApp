package com.plcoding.mvvmtodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.mvvmtodoapp.ui.Screens
import com.plcoding.mvvmtodoapp.ui.add_edit_todo.AddEditTodoScreen
import com.plcoding.mvvmtodoapp.ui.theme.MVVMTodoAppTheme
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.TodoListScreen.route
                ) {


                    composable(route = Screens.TodoListScreen.route) {
                        TodoListScreen(

                            onNavigate = {

                                //  UIEvent.Navigate(it.route + "/{todoId}")
                                navController.navigate(it.route)

                            })
                    }

                    composable(
                        route = "${Screens.AddEditTodoScreen.route}/{todoId}",
                        arguments = listOf(
                            navArgument(
                                name = "todoId",
                                builder = {
                                    type = NavType.IntType
                                    defaultValue = -1
                                })
                        )
                    ) {

                        AddEditTodoScreen(onPopBackStack = { navController.popBackStack()})
                    }

                }

            }
        }
    }
}