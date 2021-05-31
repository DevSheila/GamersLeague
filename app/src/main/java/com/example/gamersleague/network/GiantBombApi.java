package com.example.gamersleague.network;

import com.example.gamersleague.models.GiantBombGamesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GiantBombApi {
    @Headers({"Content-Type: application/json","Accept: application/json"})

    @GET("games")
    Call<GiantBombGamesResponse> getResults(@Query("api_key") String key, @Query("format") String format);
}
