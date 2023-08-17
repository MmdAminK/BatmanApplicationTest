package com.app.batman.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.batman.R
import com.app.batman.adapters.RateAdapter
import com.app.batman.core.datamodel.DataState
import com.app.batman.databinding.FragmentFilmDetailBinding
import com.app.batman.models.Film
import com.app.batman.utilities.LogicUtils.onBackPressed
import com.app.batman.utilities.ShimmerLoadingInit
import com.app.batman.utilities.ViewUtils.showInternetWarningToast
import com.app.batman.viewmodels.FilmDetailViewModel
import com.app.batman.viewmodels.FilmDetailsEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FilmDetail : Fragment() {
    private val viewModel: FilmDetailViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentFilmDetailBinding
    private var imdbId: String = ""
    private lateinit var shimmerLoadingInit: ShimmerLoadingInit

    @Inject
    lateinit var rateAdapter: RateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imdbId = requireArguments().getString("imdbId").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_film_detail, container, false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmerLoadingInit = ShimmerLoadingInit(binding.shimmerLayout, binding.mainLayout)
        shimmerLoadingInit.startShimmer()
        navController = Navigation.findNavController(view)
        binding.film = Film()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.setEvent(FilmDetailsEvent.ShowFilmDetailsEvent(imdbId))
                    viewModel.filmDetailsStateFlow.collect { dataState ->
                        when (dataState) {
                            is DataState.Loading -> {
                                shimmerLoadingInit.startShimmer()
                            }

                            is DataState.Success -> {
                                dataState.data?.let { film ->
                                    if (!film.response.isNullOrEmpty()) {
                                        shimmerLoadingInit.stopShimmer()
                                        binding.film = film
                                        rateAdapter.setRates(film.ratings)
                                        binding.reteRecyclerView.apply {
                                            layoutManager = LinearLayoutManager(
                                                context,
                                                LinearLayoutManager.HORIZONTAL,
                                                false
                                            )
                                            adapter = rateAdapter
                                        }
                                    }
                                }
                            }

                            is DataState.Error -> {
                                binding.root.showInternetWarningToast()
                            }

                            is DataState.RetrofitError -> {
                                binding.root.showInternetWarningToast()
                            }
                        }
                    }
                }
            }
        }
        onBackPressed(requireActivity()) {
            navController.popBackStack()
        }
    }
}