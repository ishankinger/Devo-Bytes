//package com.example.devo_bytes.detail
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.devo_bytes.domain.Video
//
//class DetailVideoViewModelFactory(
//    private val video: Video
//) : ViewModelProvider.Factory {
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(DetailVideoViewModel::class.java)) {
//            return DetailVideoViewModel(video) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}