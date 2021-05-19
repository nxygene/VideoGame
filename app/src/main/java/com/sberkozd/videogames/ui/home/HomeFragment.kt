package com.sberkozd.videogames.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sberkozd.videogames.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.fragmentHomeViewPager.adapter = ViewPagerAdapter()
        binding.fragmentHomeRvGameList.adapter = GameListAdapter(true)

        binding.fragmentDetailEtSearch.addTextChangedListener { text ->
            if (!text.isNullOrEmpty() && text.count() > 2) {
                binding.fragmentHomeViewPager.visibility = View.GONE
                binding.fragmentHomeIndicator.visibility = View.GONE
                val filterResult = homeViewModel.gameListResponse.value?.results!!.filter {
                    it.name.lowercase().contains(text.toString().lowercase())
                }
                if (filterResult.isNullOrEmpty()) {
                    binding.fragmentHomeTvNoResult.text = "Üzgünüz, aradığınız oyun bulunamadı!"
                    binding.fragmentHomeTvNoResult.visibility = View.VISIBLE
                    binding.fragmentHomeRvGameList.visibility = View.GONE
                } else {
                    binding.fragmentHomeTvNoResult.visibility = View.GONE
                    binding.fragmentHomeRvGameList.visibility = View.VISIBLE
                    (binding.fragmentHomeRvGameList.adapter as GameListAdapter).setItems(
                        filterResult
                    )
                }
            } else {
                binding.fragmentHomeViewPager.visibility = View.VISIBLE
                binding.fragmentHomeIndicator.visibility = View.VISIBLE
                binding.fragmentHomeRvGameList.visibility = View.VISIBLE
                (binding.fragmentHomeRvGameList.adapter as GameListAdapter).setItems(homeViewModel.gameListResponse.value?.results!!)
            }
        }

        homeViewModel.gameListResponse.observe(viewLifecycleOwner, {
            homeViewModel.onDataReceived(it)
        })

        homeViewModel.viewPagerGameList.observe(viewLifecycleOwner, {
            (binding.fragmentHomeViewPager.adapter as ViewPagerAdapter).setItems(it)
            binding.fragmentHomeIndicator.setupWithViewPager(binding.fragmentHomeViewPager)
        })

        homeViewModel.gameListResponse.observe(viewLifecycleOwner, {
            (binding.fragmentHomeRvGameList.adapter as GameListAdapter).setItems(it.results)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}