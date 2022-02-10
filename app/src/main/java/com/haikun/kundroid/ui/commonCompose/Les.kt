package com.haikun.kundroid.ui.commonCompose

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.haikun.kundroid.data.Resource

/**
 * 描述:
 * 创建人: haikun
 * 创建时间: 2021/12/21 14:13
 */
@Composable
fun <T> LesState(
    state: Resource<T>,
    scaffoldState: ScaffoldState,
    loading: (() -> Unit)? = null,
    error: (() -> Unit)? = null,
    success:@Composable () -> Unit,
) {
    when (state) {
        is Resource.LoadingResource -> {
            if (loading == null) {
                LoadingDialog()
            } else {
                loading()
            }
        }
        is Resource.ErrorResource -> {
            if (error == null) {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar(state.msg)
                }
            } else {
                error()
            }

        }
        is Resource.SuccessResource -> {
            success()
        }
        else -> {

        }
    }
}