package com.example.gamersleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface GiantBombApi {
    @Headers({"Content-Type: application/json","Accept: application/json"})

    @GET("games")
    Call<GiantBombGamesResponse> getResults(@Query("api_key") String key,@Query("format") String format);
}
