package com.example.filmviewer.model

data class TVAiringList(
    val page: Int,
    val results: List<TVResult>,
    val total_pages: Int,
    val total_results: Int
)