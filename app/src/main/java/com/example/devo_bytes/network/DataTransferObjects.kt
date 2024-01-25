package com.example.devo_bytes.network

import com.example.devo_bytes.database.DatabaseVideo
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

fun NetworkObject.asDomainModel(): List<Video> {
    return videos.map {
        Video(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}

// Convert network results into database object
fun NetworkObject.asDatabaseModel(): Array<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }.toTypedArray()
}