package com.haikun.kundroid.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.response.ArticleList
import com.haikun.kundroid.data.response.ProjectTree
import com.haikun.kundroid.request.RetrofitService
import com.haikun.kundroid.request.exeRequest
import com.haikun.kundroid.request.exeRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProjectRepository @Inject constructor(private val mService: RetrofitService) {

    suspend fun projectTree(): Flow<Resource<MutableList<ProjectTree>>> {
        return exeRequestFlow {
            mService.projectTree()
        }
    }


    fun getProjectList(cid: Int) = Pager(config = PagingConfig(20)) {
        ArticleListPageSource { page: Int ->
            mService.projectList(page = page, cid = cid)
        }
    }
}
