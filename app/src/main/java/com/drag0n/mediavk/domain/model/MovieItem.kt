package com.drag0n.mediavk.domain.model

data class MovieItem(
    val author: String,
    val description: String,
    val duration: String,
    val id: String,
    val isLive: Boolean,
    val subscriber: String,
    val thumbnailUrl: String,
    val title: String,
    val uploadTime: String,
    val videoUrl: String,
    val views: String
)