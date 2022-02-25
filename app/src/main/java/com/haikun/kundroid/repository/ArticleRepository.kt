package com.haikun.kundroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.request.RetrofitService
import com.haikun.kundroid.request.exeRequest
import com.haikun.kundroid.request.exeRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(private val mService: RetrofitService) {

    fun getArticleList()=Pager(config = PagingConfig(20)){
        ArticleListPageSource(mService)
    }

    suspend fun collectArticle(id:Int): Flow<Resource<Any>> {
        return exeRequestFlow{
            mService.collect(id)
        }
    }

    fun unCollectArticle(id: Int): Flow<Resource<Any>> {
        return exeRequestFlow{
            mService.unCollect(id)
        }
    }
}

class ArticleListPageSource(private val mService: RetrofitService) : PagingSource<Int, ArticleListData>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleListData> {
        val key=params.key?:0
        val resource = exeRequest(api = {
            mService.articleList(key)
        })


        return if (resource is Resource.SuccessResource) {
            resource.data?.datas?.let {
                return LoadResult.Page(
                    it, null, if (key > 8) {//这里是假设获取了8页之后就没有下一页了
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

    override fun getRefreshKey(state: PagingState<Int, ArticleListData>): Int? {
        return null
    }

}