package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class PostRepositoryInMemoryImpl : PostRepository {

    private var post = Post(
        1,
        "Наименование автора для примера, немного длинее чтобы обрезать!",
        "18–20 апреля в Москве проходила I Международная Ассамблея Российской академии образования «Ученик в современном мире: формула успеха». Миссия Ассамблеи заключалась в акцентуации новых и оптимизации имеющихся подходов к внедрению на уровнях основного общего и среднего общего образования релевантных методов обучения и воспитания, образовательных технологий, отвечающих запросам современного общества, создание эффективной среды для личностного и предпрофессионального развития обучающихся, личностного и профессионального развития педагогов. http://www.ivanovo.ac.ru/about_the_university/news/11502/",
        "15 мая 2022 года. 14:50:34",
        false,
        999,
        compactDecimalFormat(999),
        2000,
        compactDecimalFormat(2000),
        131,
        compactDecimalFormat(131)
    )

    private  val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {

        val newCount = post.countLikes + if (post.likeByMe) -1 else 1

        post = post.copy(likeByMe = !post.likeByMe, countLikes = newCount, countLikesText = compactDecimalFormat(newCount))

        data.value = post

    }

    override fun share() {

        val newCount = post.countShare + 1

        post = post.copy(countShare = newCount, countShareText = compactDecimalFormat(newCount))

        data.value = post

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