package com.sberkozd.videogames.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sberkozd.videogames.data.responses.GamesListResponse
import com.sberkozd.videogames.network.GameService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(private val gameService: GameService) {

    private val _response = MutableLiveData<GamesListResponse>()
    val response: LiveData<GamesListResponse> = _response

    suspend fun getGames() = withContext(Dispatchers.IO) {
        val gameListResponse = gameService.getList()
        withContext(Dispatchers.Main) {
            _response.value = gameListResponse.body()
        }
    }
}