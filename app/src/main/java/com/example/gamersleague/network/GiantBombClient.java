package com.example.gamersleague.network;

import com.example.gamersleague.network.GiantBombApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.gamersleague.Constants.GIANT_BOMB_BASE_URL;

public class GiantBombClient {
    private static Retrofit retrofit;
    public static GiantBombApi getClient() {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);



            OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
//            .addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request newRequest  = chain.request().newBuilder()
//                            .addHeader("Authorization",GIANT_BOMB_KEY)
//                            .build();
//                    return chain.proceed(newRequest);
//                }
//        })
                    .build();
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(GIANT_BOMB_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


        return retrofit.create(GiantBombApi.class);

        // Define the interceptor, add authentication headers
//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request()
//                        .newBuilder()
//                        .addHeader("Authorization", GIANT_BOMB_KEY)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        };
//        // Add the interceptor to OkHttpClient
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//
//// Can be Level.BASIC, Level.HEADERS, or Level.BODY
//
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.networkInterceptors().add(httpLoggingInterceptor);
////        builder.build();
//        builder.interceptors().add(interceptor);
//        OkHttpClient client = builder.build();
//
//                Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        // Add the interceptor to OkHttpClient
//
//        // Set the custom client when building adapter
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(GIANT_BOMB_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(client)
//                .build();
//
//        return retrofit.create(GiantBombApi.class);
    }
}