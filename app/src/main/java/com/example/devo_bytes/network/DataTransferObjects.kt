package com.example.devo_bytes.network

import com.example.devo_bytes.domain.Video
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String)

@JsonClass(generateAdapter = true)
data class NetworkObject(val videos: List<NetworkVideo>)

fun List<NetworkVideo>.asDomainModel(): List<Video> {
    return map {
        Video(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}