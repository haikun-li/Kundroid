package com.haikun.kundroid.ui.screen.articledetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.repository.ArticleRepository
import com.haikun.kundroid.ui.BaseViewModel
import com.haikun.kundroid.utils.LogUtil
import com.haikun.kundroid.utils.fromJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 描述:
 * 创建人: haikun
 * 创建时间: 2022/1/20 15:04
 */
@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val articleRepository: ArticleRepository) : BaseViewModel() {

    var articleDetailUiState by mutableStateOf(ArticleDetailUiState(false, "", "", 0, ""))
    override fun initUiState(navHostController: NavHostController, vararg jsons: String?) {
        super.initUiState(navHostController, *jsons)
        jsons[0]?.let {
            val articleListData = fromJson<ArticleListData>(it)
            articleListData?.let { data ->
                articleDetailUiState =
                    articleDetailUiState.copy(
                        isCollect = data.collect,
                        title = data.title,
                        link = data.link,
                        id = data.id,
                        author = data.author
                    )
            }
        }
    }

    fun delCollect() {
        if (articleDetailUiState.isCollect) {
            viewModelScope.launch {
                articleRepository.unCollectArticle(articleDetailUiState.id).collect {
                    if (it is Resource.SuccessResource){
                        articleDetailUiState=articleDetailUiState.copy(isCollect = false)
                    }
                }
            }
        } else {
            viewModelScope.launch {
                articleRepository.collectArticle(articleDetailUiState.id).collect {
                    if (it is Resource.SuccessResource){
                        articleDetailUiState=articleDetailUiState.copy(isCollect = true)
                    }
                }

            }
        }
    }


}