package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likecount: Int,
    val share: Int,
    val video: String? = null,
    val isLiked: Boolean
)

