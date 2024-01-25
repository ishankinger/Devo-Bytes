//package com.example.devo_bytes.detail
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.ViewModelProviders
//import com.example.devo_bytes.R
//import com.example.devo_bytes.databinding.FragmentDetailVideoBinding
//import com.example.devo_bytes.databinding.FragmentTitleBinding
//
//class DetailVideoFragment : Fragment() {
//    private lateinit var viewModel : DetailVideoViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        val binding : FragmentDetailVideoBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_detail_video,container,false)
//
//        val video = DetailVideoFragmentArgs.fromBundle(requireArguments()).selectedVideo
//
//        val viewModelFactory = DetailVideoViewModelFactory(video)
//
//        viewModel = ViewModelProviders.of(this).get(DetailVideoViewModel::class.java)
//
//        binding.detailVideo = viewModel
//
//        binding.lifecycleOwner = this
//
//        return binding.root
//    }
//}