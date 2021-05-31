package com.example.gamersleague;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.gamersleague.Constants.GIANT_BOMB_BASE_URL;
import static com.example.gamersleague.Constants.GIANT_BOMB_KEY;

public class GiantBombClient {
    private static Retrofit retrofit = null;
    public static GiantBombApi getClient() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization",GIANT_BOMB_KEY)
                            .build();
                    return chain.proceed(newRequest);
                }
            })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(GIANT_BOMB_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(GiantBombApi.class);
    }
}