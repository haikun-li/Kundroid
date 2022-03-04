package com.haikun.kundroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haikun.kundroid.data.AppResponse
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.response.ArticleList
import com.haikun.kundroid.request.RetrofitService
import com.haikun.kundroid.request.exeRequest
import com.haikun.kundroid.request.exeRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(private val mService: RetrofitService) {

    fun getArticleList() = Pager(config = PagingConfig(20)) {
        ArticleListPageSource { page: Int ->
            mService.articleList(page = page)
        }
    }

    fun collectList() = Pager(config = PagingConfig(20)) {
        ArticleListPageSource { page: Int ->
            mService.collectList(page = page)
        }
    }

    suspend fun collectArticle(id: Int): Flow<Resource<Any>> {
        return exeRequestFlow {
            mService.collect(id)
        }
    }

    suspend fun unCollectArticle(id: Int, originId: Int, ifFromCollect: Boolean): Flow<Resource<Any>> {
        return exeRequestFlow {
            if (ifFromCollect){
                mService.unCollectFromCollect(id,originId)
            }else{
                mService.unCollect(id)
            }
        }
    }
}

class ArticleListPageSource<T : Any>(private val api: suspend (Int) -> AppResponse<ArticleList<T>>) :
    PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val key = params.key ?: 1
        val resource = exeRequest(api = {
            api(key)
        })


        return if (resource is Resource.SuccessResource) {
            resource.data?.let {
                return LoadResult.Page(
                    it.datas, null, if (key ==it.pageCount) {
                        null
                    } else {
                        key + 1
                    }
                )
            }
            LoadResult.Error(Exception())
        } else {
            LoadResult.Error(Exception())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return null
    }

}