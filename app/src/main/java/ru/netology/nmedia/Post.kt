package ru.netology.nmedia

data class Post(

    val id: Int,
    val author: String,
    val content: String,
    val publishedDate: String,
    val likeByMe: Boolean,
    val countLikes: Int,
    val countLikesText: String,
    val countShare: Int,
    val countShareText: String,
    val countVisibility: Int,
    val countVisibilityText: String

    )
