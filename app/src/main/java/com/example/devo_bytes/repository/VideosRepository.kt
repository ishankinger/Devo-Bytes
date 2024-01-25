package com.example.devo_bytes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.devo_bytes.database.DatabaseVideo
import com.example.devo_bytes.database.VideosDatabase
import com.example.devo_bytes.database.asDomainModel
import com.example.devo_bytes.domain.Video
import com.example.devo_bytes.network.DevByteViewersApi
import com.example.devo_bytes.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database : VideosDatabase) {

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO) {
            val playlist = DevByteViewersApi.retrofitService.getPlaylistAsync().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }

}