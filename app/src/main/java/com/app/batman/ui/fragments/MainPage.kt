package com.app.batman.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.app.batman.R
import com.app.batman.adapters.FilmsAdapter
import com.app.batman.core.datamodel.DataState
import com.app.batman.databinding.FragmentMainPageBinding
import com.app.batman.models.Film
import com.app.batman.utilities.LogicUtils.onBackPressed
import com.app.batman.utilities.ShimmerLoadingInit
import com.app.batman.utilities.ViewUtils.showInternetWarningToast
import com.app.batman.utilities.viewpager.ViewPagerInit
import com.app.batman.viewmodels.FilmsEvent
import com.app.batman.viewmodels.FilmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainPage : Fragment() {
    private val viewModel: FilmsViewModel by viewModels()
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var navController: NavController
    private lateinit var shimmerLoadingInit: ShimmerLoadingInit

    @Inject
    lateinit var filmsAdapter: FilmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_main_page, container, false
            )
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmerLoadingInit = ShimmerLoadingInit(binding.shimmerLayout, binding.mainPageViewPager)
        shimmerLoadingInit.startShimmer()
        navController = Navigation.findNavController(view)
        showPageNumber()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.setEvent(FilmsEvent.ShowFilmsEvent)
                    viewModel.filmsSharedFlow.collect { dataState ->
                        when (dataState) {
                            is DataState.Loading -> {
                                shimmerLoadingInit.startShimmer()
                            }

                            is DataState.Success -> {
                                dataState.data?.let { films ->
                                    if (!films.isNullOrEmpty()) {
                                        shimmerLoadingInit.stopShimmer()
                                        initAdapter(films)
                                        viewPagerInit()
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
            createExitAlertDialog()
        }
    }

    //TODO Add to ViewUtils
    private fun createExitAlertDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Are you sure?")
        builder.setNegativeButton("No") { _, _ -> }
        builder.setPositiveButton("Yes") { _, _ ->
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        builder.create().show()
    }

    private fun viewPagerInit() {
        binding.mainPageViewPager.adapter = filmsAdapter

        ViewPagerInit.Builder()
            .clipChildren(false)
            .clipToPadding(false)
            .offscreenPageLimit(3)
            .build()
            .run {
                binding.mainPageViewPager.show()
            }

    }

    private fun initAdapter(films: List<Film>) {
        filmsAdapter.setFilms(films)
        filmsAdapter.setOnItem { position ->
            onCardClick(films[position].imdbId, position)
        }
    }

    private fun onCardClick(imdbId: String, position: Int) {
        val bundle = bundleOf("imdbId" to imdbId)
        viewModel.page = position
        navController.navigate(
            R.id.action_mainPage_to_filmDetail, bundle
        )
    }

    private fun showPageNumber() {
        binding.mainPageViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
                binding.slideCounter.text = "${position + 1} / 10"
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }
}