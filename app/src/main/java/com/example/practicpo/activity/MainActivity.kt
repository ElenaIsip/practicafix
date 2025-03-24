package com.example.practicpo.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practicpo.R
import com.example.practicpo.adapter.PostsAdapter
import com.example.practicpo.databinding.ActivityMainBinding
import com.example.practicpo.databinding.ActivitymakBinding
import com.example.practicpo.viewlmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter {
            viewModel.likeById(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts
        }
    }
}





