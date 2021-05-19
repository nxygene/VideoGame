package com.sberkozd.videogames.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sberkozd.videogames.data.responses.GameDetailResponse
import com.sberkozd.videogames.data.responses.GamesListResponse
import com.sberkozd.videogames.network.GameService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailRepository @Inject constructor(private val gameService: GameService) {

    private val _response = MutableLiveData<GameDetailResponse>()
    val response: LiveData<GameDetailResponse> = _response

    suspend fun getGameDetails(id: Int) = withContext(Dispatchers.IO) {
        val gameDetailResponse = gameService.getGameDetails(id)
        withContext(Dispatchers.Main) {
            _response.value = gameDetailResponse.body()
        }
    }
}