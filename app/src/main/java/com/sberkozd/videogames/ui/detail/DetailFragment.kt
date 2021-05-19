package com.sberkozd.videogames.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.sberkozd.videogames.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val isFavorite = preferences?.getBoolean(arguments?.get("id").toString(), false)!!
        binding.fragmentDetailBtnFavorite.isChecked = isFavorite

        detailViewModel.gameDetailResponse.observe(viewLifecycleOwner, { response ->
            binding.apply {
                fragmentDetailIvPoster.load(response.background_image)
                fragmentDetailTvDescription.text = response.description_raw
                fragmentDetailTvRate.text = response.metacritic?.toString()
                fragmentDetailTvReleaseDate.text = response.released
                fragmentDetailTvTitle.text = response.name
                fragmentDetailBtnFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        preferences.edit {
                            putBoolean(response.id.toString(), true)
                        }
                    } else {
                        preferences.edit {
                            remove(response.id.toString())
                        }
                    }
                }
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}