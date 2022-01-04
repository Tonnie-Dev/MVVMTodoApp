package com.plcoding.mvvmtodoapp.util

sealed class UIEvent {


    //navigate back
    object PopBackStack : UIEvent()
    data class Navigate(val route: String) : UIEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ) : UIEvent()
}
