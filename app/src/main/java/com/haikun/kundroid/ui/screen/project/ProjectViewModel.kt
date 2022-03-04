package com.haikun.kundroid.ui.screen.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.response.ArticleListData
import com.haikun.kundroid.data.response.ProjectListData
import com.haikun.kundroid.data.response.ProjectTree
import com.haikun.kundroid.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val projectRepository: ProjectRepository) : ViewModel() {

    var selectedTabIndex by mutableStateOf(0)

    var classify by mutableStateOf(
        mutableListOf<ProjectTree>()
    )
    //val classify = _classify

    init {
        getProjectTree()
    }

    private fun getProjectTree() {
        viewModelScope.launch {
            projectRepository.projectTree().collect {
                if (it is Resource.SuccessResource) {
                    it.data?.let { list ->
                        classify = list
                    }
                }
            }
        }
    }

    fun getPagingDataFlow(): Flow<PagingData<ProjectListData>> {
        return  projectRepository.getProjectList(classify[selectedTabIndex].id).flow.cachedIn(viewModelScope)
    }
}