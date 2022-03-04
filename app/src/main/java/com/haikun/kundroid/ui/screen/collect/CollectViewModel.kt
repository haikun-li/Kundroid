package com.haikun.kundroid.ui.screen.collect

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.repository.ArticleRepository
import com.haikun.kundroid.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CollectViewModel @Inject constructor(private val articleRepository: ArticleRepository) : BaseViewModel() {



    fun flow(): Flow<PagingData<ArticleListData>> {
        return articleRepository.collectList().flow.cachedIn(viewModelScope)
    }
}