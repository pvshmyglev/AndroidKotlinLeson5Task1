package ru.netology.nmedia

import android.icu.text.CompactDecimalFormat
import android.os.Bundle
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

        val post = Post(
            1,
            "Наименование автора для примера, немного длинее чтобы обрезать!",
            "18–20 апреля в Москве проходила I Международная Ассамблея Российской академии образования «Ученик в современном мире: формула успеха». Миссия Ассамблеи заключалась в акцентуации новых и оптимизации имеющихся подходов к внедрению на уровнях основного общего и среднего общего образования релевантных методов обучения и воспитания, образовательных технологий, отвечающих запросам современного общества, создание эффективной среды для личностного и предпрофессионального развития обучающихся, личностного и профессионального развития педагогов. http://www.ivanovo.ac.ru/about_the_university/news/11502/",
            "15 мая 2022 года. 14:50:34",
             false,
            999,
            2000,
            131
        )

        binding.apply {
            textViewAuthorName.text = post.author
            textViewAuthorDate.text = post.publishedDate
            textViewContent.text = post.content
            TextViewFavoriteBorder.text = compactDecimalFormat(post.countLikes)
            TextViewShare.text = compactDecimalFormat(post.countShare)
            TextViewVisibility.text = compactDecimalFormat(post.countVisibility)


            setImageAndTextLike(post, binding)

            ImageViewFavoriteBorder.setOnClickListener {

                post.likeByMe = !post.likeByMe

                post.countLikes = post.countLikes + if (post.likeByMe) 1 else -1

                setImageAndTextLike(post, binding)

            }


            ImageViewShare.setOnClickListener {

                post.countShare = post.countShare + 1

                TextViewShare.text = compactDecimalFormat(post.countShare)

            }

        }

    }

    private fun setImageAndTextLike (post: Post, binding: ActivityMainBinding){

        binding.apply {

            binding.ImageViewFavoriteBorder.setImageResource(

                if (post.likeByMe) R.drawable.ic_baseline_favorite_24_red else R.drawable.ic_baseline_favorite_border_24

            )

            binding.TextViewFavoriteBorder.text = compactDecimalFormat(post.countLikes)

        }

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