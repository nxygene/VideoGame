package com.sberkozd.videogames.ui.detail

import androidx.lifecycle.*
import com.sberkozd.videogames.data.responses.GameDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
    state: SavedStateHandle
) : ViewModel() {

    private val gameId = state.get<Int>("id")

    private val _text = MutableLiveData<String>().apply {
        value = "This is detail Fragment"
    }
    val text: LiveData<String> = _text

    val gameDetailResponse: LiveData<GameDetailResponse> = repository.response

    init {
        viewModelScope.launch {
            repository.getGameDetails(gameId!!)
        }
    }
}