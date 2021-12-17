package com.augurit.kundroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.augurit.kundroid.data.Resource
import com.augurit.kundroid.data.response.Content
import com.augurit.kundroid.request.RetrofitService
import com.augurit.kundroid.request.exeRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val mService: RetrofitService) {

    fun getContentByTitle(title:String)=Pager(config = PagingConfig(20)){
        ContentPageSource(title,mService)
    }
}

class ContentPageSource(private val title: String, private val mService: RetrofitService) : PagingSource<Int, Content>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        val key=params.key?:0
        val resource = exeRequest {
            mService.getContentByTitle(key, title)
        }

        return if (resource is Resource.SuccessResource) {
            resource.data?.let {
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

    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return null
    }

}