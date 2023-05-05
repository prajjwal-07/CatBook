package com.example.catbook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.catbook.R
import com.example.catbook.databinding.FragmentCatHomeBinding
import com.example.catbook.epoxy.controller.CatBreedController
import com.example.catbook.interfaces.CatHomeListener
import com.example.catbook.interfaces.ClickType
import com.example.catbook.viewModel.CatHomeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CatHomeFragment : DaggerFragment(), CatHomeListener {

    @Inject
    lateinit var viewModel: CatHomeViewModel

    @Inject
    lateinit var controller: CatBreedController

    private var _binding: FragmentCatHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCatBreedData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
        setUpEpoxy()
        addListScrollListener()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun addListener() {
        binding.seeCatBtn.setOnClickListener {
            this.onClicked(
                ClickType.SEE_CAT_IMAGE_BUTTON_CLICK,
                viewModel.selectedCatBreedId,
            )
        }
    }

    private fun setUpEpoxy() {
        binding.rvList.setController(controller)
        addObserver()
    }

    override fun <T> onClicked(type: ClickType, data: T?, index: Int?) {
        when (type) {
            ClickType.CAT_BREED_CARD_CLICK -> {
                when (data) {
                    is Pair<*, *> -> {
                        if (data.second == false) {
                            viewModel.selectedCatBreedId.add(data.first as String)
                        } else {
                            viewModel.selectedCatBreedId.remove(data.first)
                        }
                        controller.selectedCatBreedId = viewModel.selectedCatBreedId
                    }
                }
            }

            ClickType.SEE_CAT_IMAGE_BUTTON_CLICK -> {
                goToCatImageFragment(viewModel.selectedCatBreedId)
            }
        }
    }

    private fun goToCatImageFragment(breedIds: List<String>) {
        val navController = findNavController()
        if (navController.currentDestination?.id == R.id.catHomeFragment) {
            val action =
                CatHomeFragmentDirections.actionCatHomeFragmentToCatImageFragment(breedIds.toTypedArray())
            navController.navigate(action)
        }
    }

    private fun addObserver() {
        viewModel.catBreedDataLiveData.observe(viewLifecycleOwner) {
            controller.data = it
        }
    }

    private fun addListScrollListener() {
        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchCatBreedData()
                }
            }
        })
    }


}