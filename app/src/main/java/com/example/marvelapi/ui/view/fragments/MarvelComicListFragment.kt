package com.example.marvelapi.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelapi.R
import com.example.marvelapi.data.model.Result
import com.example.marvelapi.databinding.MarvelComicFragmentBinding
import com.example.marvelapi.ui.view.adapters.ComicAdapter
import com.example.marvelapi.ui.viewmodel.MarvelViewModel
import com.example.marvelapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelComicListFragment : Fragment() {

    private val marvelViewModel: MarvelViewModel by activityViewModels()
    private var _binding: MarvelComicFragmentBinding? = null
    private val binding get() = _binding!!
    private val comicAdapter: ComicAdapter by lazy {
        ComicAdapter(this::onComicSelected)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MarvelComicFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            comicRv.apply {
                adapter = comicAdapter
            }

            marvelViewModel.marvelResource.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                        Toast.makeText(requireContext(), it.errorMsg, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        comicAdapter.updateList(it.data)
                    }
                }
            }
        }
    }

    private fun onComicSelected(result: Result) {
        marvelViewModel.setSelectedComic(result)
        findNavController().navigate(R.id.marvel_comic_details_fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}