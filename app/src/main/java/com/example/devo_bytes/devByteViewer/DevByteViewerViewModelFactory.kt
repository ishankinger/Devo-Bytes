package com.example.devo_bytes.devByteViewer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DevByteViewerViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DevByteViewerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DevByteViewerViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}