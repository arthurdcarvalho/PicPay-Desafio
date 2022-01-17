package com.picpay.desafio.android.ui.viewmodel.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.picpay.desafio.data.util.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun launch(
        block: suspend CoroutineScope.() -> Unit,
        loading: (Boolean) -> Unit,
        error: (String) -> Unit = {},
    ): Job {
        return uiScope.launch {
            try {
                loading.invoke(true)
                block()
            } catch (exception: Exception) {
                exception.message?.let { message -> error.invoke(message) }
            } finally {
                loading.invoke(false)
            }
        }
    }
}