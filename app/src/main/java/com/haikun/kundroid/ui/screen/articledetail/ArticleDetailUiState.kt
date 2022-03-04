package com.haikun.kundroid.ui.screen.articledetail

data class ArticleDetailUiState(
    val isCollect: Boolean,
    val title: String,
    val link: String,
    val id: Int,
    val originId: Int,
    val author: String,
)
