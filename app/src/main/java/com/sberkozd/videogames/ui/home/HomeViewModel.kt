package com.sberkozd.videogames.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sberkozd.videogames.data.models.Game
import com.sberkozd.videogames.data.responses.GamesListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: HomeRepository) : ViewModel() {

    val gameListResponse: LiveData<GamesListResponse> = repository.response

    val viewPagerGameList: MutableLiveData<List<Game>> = MutableLiveData()

    fun onDataReceived(response: GamesListResponse) {
        viewPagerGameList.value = response.results.take(3)
    }

    init {
        viewModelScope.launch {
            repository.getGames()
        }
    }
}