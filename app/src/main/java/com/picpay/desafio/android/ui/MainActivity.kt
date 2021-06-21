package com.picpay.desafio.android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.util.observe
import com.picpay.desafio.android.ui.viewmodel.ScreenState
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(userViewModel)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        bindObservers()
    }

    private fun initComponents() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun bindObservers() {
        observe(userViewModel.viewState.state) { state ->
            when (state) {
                is ScreenState.Success -> showSuccessLayout(state)
                is ScreenState.Empty -> { /* */ }
                is ScreenState.NoConnection -> { /* */ }
                is ScreenState.Error -> showErrorLayout(state)
            }
        }
        observe(userViewModel.viewState.isLoading, ::showLoadingLayout)
    }

    private fun showSuccessLayout(state: ScreenState.Success) {
        adapter.users = state.users
    }

    private fun showLoadingLayout(isLoading: Boolean) {
        binding.userListProgressBar.isVisible = isLoading
    }

    private fun showErrorLayout(state: ScreenState.Error) {
        binding.userListProgressBar.isVisible = false
        binding.recyclerView.isVisible = false

        Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }
}
