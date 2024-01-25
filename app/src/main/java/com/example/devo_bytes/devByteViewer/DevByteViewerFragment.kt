package com.example.devo_bytes.devByteViewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.devo_bytes.R
import com.example.devo_bytes.databinding.FragmentDevByteViewerBinding
import com.example.devo_bytes.domain.Video


class DevByteViewerFragment : Fragment() {

    private lateinit var viewModel : DevByteViewerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentDevByteViewerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dev_byte_viewer,container,false)

        val viewModelFactory = activity?.let { DevByteViewerViewModelFactory(it.application) }

        viewModel = ViewModelProviders.of(this,viewModelFactory)[DevByteViewerViewModel::class.java]

        binding.devByteViewModel = viewModel

        binding.lifecycleOwner = this

        binding.recyclerView.adapter = DevByteVideoAdapter(
            DevByteVideosListener{
            Toast.makeText(context,"Video was clicked",LENGTH_SHORT).show()
            // When a video is clicked this block or lambda will be called by DevByteAdapter

            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            val packageManager = context?.packageManager ?: return@DevByteVideosListener

            // Try to generate a direct intent to the YouTube app
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if(intent.resolveActivity(packageManager) == null) {
                // YouTube app isn't found, use the web url
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }
            startActivity(intent)
            },
            DevByteVideosListener2 {
                //viewModel.displayVideoDetails(it)
                Toast.makeText(context,"Description was clicked", LENGTH_SHORT).show()
            }
        )

//        viewModel.navigateToSelectedVideo.observe(viewLifecycleOwner, Observer {
//            if ( null != it ) {
//                this.findNavController().navigate(DevByteViewerFragmentDirections.
//                actionDevByteFragmentToDetailVideoFragment())
//                viewModel.displayVideoDetailsComplete()
//            }
//        })

        return binding.root
    }

    private val Video.launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }
}