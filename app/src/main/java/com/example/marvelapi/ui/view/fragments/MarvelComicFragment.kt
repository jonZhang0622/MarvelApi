package com.example.marvelapi.ui.view.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.marvelapi.ui.viewmodel.MarvelViewModel

class MarvelComicFragment : Fragment() {

    private val marvelViewModel : MarvelViewModel by activityViewModels()
}