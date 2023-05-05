package com.example.catbook.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.catbook.data.local.CatImageData
import com.example.catbook.databinding.FragmentCatImageBinding
import com.example.catbook.epoxy.controller.CatImageController
import com.example.catbook.viewModel.CatImageViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CatImageFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: CatImageViewModel

    @Inject
    lateinit var controller: CatImageController

    private var _binding: FragmentCatImageBinding? = null
    private val binding get() = _binding!!

    private val args: CatImageFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.breedIdList = args.breedId
        viewModel.fetchCatImageData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEpoxy()
        setUpListener()
        addListScrollListener()
    }

    private fun setUpEpoxy() {
        binding.rvList.setController(controller)
        addObserver()
    }

    private fun addObserver() {
        viewModel.catImageDataLiveData.observe(viewLifecycleOwner) {
            controller.data = it
        }
    }

    private fun setUpListener() {
        binding.header.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun addListScrollListener() {
        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchCatImageData()
                }
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}