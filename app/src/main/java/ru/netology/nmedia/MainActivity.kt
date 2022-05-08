package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this) {post ->


            binding.apply {
                textViewAuthorName.text = post.author
                textViewAuthorDate.text = post.publishedDate
                textViewContent.text = post.content
                TextViewFavoriteBorder.text = post.countLikesText
                TextViewShare.text = post.countShareText
                TextViewVisibility.text = post.countVisibilityText

                binding.ImageViewFavoriteBorder.setImageResource(
                    if (post.likeByMe) R.drawable.ic_baseline_favorite_24_red else R.drawable.ic_baseline_favorite_border_24
                )

            }

        }

        binding.ImageViewFavoriteBorder.setOnClickListener { viewModel.like() }
        binding.ImageViewShare.setOnClickListener { viewModel.share() }

    }


}