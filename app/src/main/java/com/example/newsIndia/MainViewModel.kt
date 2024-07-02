package com.example.newsIndia

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsIndia.presentation.navgraph.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel :
    ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        viewModelScope.launch {
            startDestination = Route.NewsNavigation.route
            delay(300)
            splashCondition = false
        }
    }
}