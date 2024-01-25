//package com.example.devo_bytes.detail
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.devo_bytes.domain.Video
//
//class DetailVideoViewModel(video : Video) : ViewModel() {
//
//    private val _selectedVideo = MutableLiveData<Video>()
//    val selectedVideo: LiveData<Video>
//        get() = _selectedVideo
//
//    init{
//        _selectedVideo.value = video
//    }
//
//
//    override fun onCleared(){
//        super.onCleared()
//    }
//}