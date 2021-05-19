package com.sberkozd.videogames.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sberkozd.videogames.databinding.FragmentFavoriteBinding
import com.sberkozd.videogames.ui.home.GameListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.fragmentFavoriteRvGameList.adapter = GameListAdapter(false)

        val preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val allFavorites = preferences?.all!!.keys

        favoriteViewModel.gameListResponse.observe(viewLifecycleOwner, { it ->
            (binding.fragmentFavoriteRvGameList.adapter as GameListAdapter).setItems(it.results.filter {
                allFavorites.contains(
                    it.id.toString()
                )
            })
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}