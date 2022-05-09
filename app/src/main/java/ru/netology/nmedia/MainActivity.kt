package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

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
                TextViewFavoriteBorder.text = compactDecimalFormat(post.countLikes)
                TextViewShare.text = compactDecimalFormat(post.countShare)
                TextViewVisibility.text = compactDecimalFormat(post.countVisibility)

                binding.ImageViewFavoriteBorder.setImageResource(
                    if (post.likeByMe) R.drawable.ic_baseline_favorite_24_red else R.drawable.ic_baseline_favorite_border_24
                )

            }

        }

        binding.ImageViewFavoriteBorder.setOnClickListener { viewModel.like() }
        binding.ImageViewShare.setOnClickListener { viewModel.share() }

    }

    private fun compactDecimalFormat(number: Int): String {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')

        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3

        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }


}