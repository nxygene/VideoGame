package com.sberkozd.videogames.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sberkozd.videogames.data.models.Game
import com.sberkozd.videogames.data.responses.GamesListResponse
import com.sberkozd.videogames.ui.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val gameListResponse: LiveData<GamesListResponse> = repository.response

    init {
        viewModelScope.launch {
            repository.getGames()
        }
    }
}