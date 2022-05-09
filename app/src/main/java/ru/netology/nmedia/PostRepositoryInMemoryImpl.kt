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
        2000,
        131
    )

    private  val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {

        val newCount = post.countLikes + if (post.likeByMe) -1 else 1

        post = post.copy(likeByMe = !post.likeByMe, countLikes = newCount)

        data.value = post

    }

    override fun share() {

        post = post.copy(countShare = post.countShare + 1)

        data.value = post

    }

}