package com.example.marvelapi.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.marvelapi.databinding.MarvelComicDetailsFragmentBinding
import com.example.marvelapi.ui.viewmodel.MarvelViewModel
import com.example.marvelapi.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelComicDetailsFragment : Fragment() {

    private val marvelViewModel: MarvelViewModel by activityViewModels()
    private var _binding: MarvelComicDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MarvelComicDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            marvelViewModel.selectedComic.observe(viewLifecycleOwner) {
                comicIv.loadImage(it.thumbnail.path)
                titleTv.text = it.title
                descriptionTv.text = it.description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}