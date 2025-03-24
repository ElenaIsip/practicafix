package com.example.practicpo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicpo.R
import com.example.practicpo.databinding.ActivitymakBinding
import com.example.practicpo.activity.Post

typealias OnLikeListener = (post: Post) -> Unit

class PostsAdapter(private val onLikeListener: OnLikeListener) : RecyclerView.Adapter<PostViewHolder>() {
    var list = emptyList<Post>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivitymakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}
private var likesCount = 0
private var sharesCount = 0
private var viewsCount = 0
private var liked = false
class PostViewHolder(
    private val binding: ActivitymakBinding,
    private val onLikeListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
//            like.setImageResource(
//                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
//            )
            if (post.likedByMe) {
                like2.setImageResource(R.drawable.heart)
            }
            like2.setOnClickListener{
                onLikeListener(post)
            }

        }
        binding.like2.setOnClickListener {
            liked = !liked
            if (liked) {
                likesCount++
                binding.like2.setImageResource(R.drawable.heart)
            } else {
                likesCount--
                binding.like2.setImageResource(R.drawable.hert)
            }
            binding.likesCount2.text = formatCount(likesCount)
        }

        binding.share2.setOnClickListener {
            sharesCount++
            binding.shareCount2.text = formatCount(sharesCount)
        }

        viewsCount = 999999
        binding.viewCount2.text = formatCount(viewsCount)
    }

    private fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM", count.toDouble() / 1_000_000)
            count >= 1_000 -> String.format("%dK", count / 1_000)
            else -> count.toString()
        }
    }
}
