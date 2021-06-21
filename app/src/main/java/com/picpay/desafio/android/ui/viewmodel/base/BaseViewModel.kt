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

abstract class BaseViewModel(
    private val network: Network
) : ViewModel(), LifecycleObserver, KoinComponent {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun launch(
        block: suspend CoroutineScope.() -> Unit,
        loading: (Boolean) -> Unit,
        error: (String) -> Unit = {},
        checkConnection: Boolean = true
    ): Job {
        return uiScope.launch {
            if ((checkConnection && network.hasInternetConnection()) || !checkConnection) {
                try {
                    loading.invoke(true)
                    block()
                } catch (exception: Exception) {
                    exception.message?.let { message -> error.invoke(message) }
                } finally {
                    loading.invoke(false)
                }
            } else error.invoke("Sem conexão com a internet")
        }
    }
}