package com.mubarakansari.ktor_framework_android_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mubarakansari.ktor_framework_android_kotlin.Adapter.PostAdapter
import com.mubarakansari.ktor_framework_android_kotlin.DaggerHilt.ViewModel.MainViewModel
import com.mubarakansari.ktor_framework_android_kotlin.DaggerHilt.ui.ApiState
import com.mubarakansari.ktor_framework_android_kotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()

        mainViewModel.getPost()
        lifecycleScope.launchWhenStarted {
            mainViewModel.apiStateFlow.collect {
               binding.apply {
                   when(it){
                       is ApiState.Success->{
                           recyclerView.isVisible= true
                           postAdapter.submitList(it.data)
                       }
                       is ApiState.Failure ->{
                           recyclerView.isVisible= false
                       }
                   }
               }
            }

        }

    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = postAdapter
            }
        }
    }
}