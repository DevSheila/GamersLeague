package com.example.gamersleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiantBombApi {
    @GET("/games")
    Call<GiantBombGamesResponse> getResults();
}
