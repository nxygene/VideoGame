package com.sberkozd.videogames.network

import com.sberkozd.videogames.data.responses.GameDetailResponse
import com.sberkozd.videogames.data.responses.GamesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {

    @GET("games")
    suspend fun getList(): Response<GamesListResponse>

    @GET ("games/{id}")
    suspend fun getGameDetails(@Path("id") id: Int): Response<GameDetailResponse>
}