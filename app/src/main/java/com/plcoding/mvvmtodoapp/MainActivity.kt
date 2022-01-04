package com.plcoding.mvvmtodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.mvvmtodoapp.ui.Screens
import com.plcoding.mvvmtodoapp.ui.add_edit_todo.AddEditTodoScreen
import com.plcoding.mvvmtodoapp.ui.theme.MVVMTodoAppTheme
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoListScreen
import com.plcoding.mvvmtodoapp.util.UIEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppTheme {
                
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.TodoListScreen.route) {
                    
                    
                    composable(route = Screens.TodoListScreen.route){
                        TodoListScreen(
                            
                            onNavigate =  { 
                                
                                UIEvent.Navigate(it.route + "/{todoId}")
                                
                            
                            })
                    }

                    composable(route = Screens.AddEditTodoScreen.route + "/{todoId}"){
                        
                        AddEditTodoScreen(onPopBackStack = {  })
                    }

                }

            }
        }
    }
}