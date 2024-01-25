package com.example.devo_bytes.devByteViewer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devo_bytes.database.getDatabase
import com.example.devo_bytes.domain.Video
import com.example.devo_bytes.network.DevByteViewersApi
import com.example.devo_bytes.network.asDomainModel
import com.example.devo_bytes.repository.VideosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class DevByteViewerApiStatus{LOADING,ERROR,DONE}

class DevByteViewerViewModel(app: Application) : ViewModel() {

    private var viewModelJob = Job()

    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    private val database = getDatabase(app)

    private val videoRepository = VideosRepository(database)

//    private val _devByteVideos = MutableLiveData<List<Video>>()
//    val devByteVideos: LiveData<List<Video>>
//        get() = _devByteVideos

    private val _status = MutableLiveData<DevByteViewerApiStatus>()
    val status: LiveData<DevByteViewerApiStatus>
        get() = _status

//    private val _navigateToSelectedVideo = MutableLiveData<Video?>()
//    val navigateToSelectedVideo: LiveData<Video?>
//        get() = _navigateToSelectedVideo

    init{
        //getDevByteVideos()
        _status.value = DevByteViewerApiStatus.LOADING
        coroutineScope.launch {
            videoRepository.refreshVideos()
        }
    }

    val devByteVideos = videoRepository.videos

//    private fun getDevByteVideos() {
//        coroutineScope.launch {
//            val getPlaylistDeferred = DevByteViewersApi.retrofitService.getPlaylistAsync()
//            try{
//                _status.value = DevByteViewerApiStatus.LOADING
//                val listResult = getPlaylistDeferred.await()
//                if(listResult.videos.isNotEmpty()){
//                    _devByteVideos.value = listResult.asDomainModel()
//                }
//                _status.value = DevByteViewerApiStatus.DONE
//            }
//            catch(e: Exception){
//                _status.value = DevByteViewerApiStatus.ERROR
//                _devByteVideos.value = ArrayList()
//            }
//
//        }
//    }
//
//    fun displayVideoDetails(video: Video) {
//        _navigateToSelectedVideo.value = video
//    }
//    fun displayVideoDetailsComplete() {
//        _navigateToSelectedVideo.value = null
//    }

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }
}