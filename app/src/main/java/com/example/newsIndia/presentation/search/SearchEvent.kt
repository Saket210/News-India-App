package com.example.newsIndia.presentation.search

sealed class SearchEvent{

    data class UpdateSearchEvent(val searchQuery: String): SearchEvent()
    object SearchNews: SearchEvent()

}
