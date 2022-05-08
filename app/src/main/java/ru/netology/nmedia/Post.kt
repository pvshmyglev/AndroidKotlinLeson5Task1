package ru.netology.nmedia

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val publishedDate: String,
    var likeByMe: Boolean,
    var countLikes: Int,
    var countShare: Int,
    var countVisibility: Int
    )
