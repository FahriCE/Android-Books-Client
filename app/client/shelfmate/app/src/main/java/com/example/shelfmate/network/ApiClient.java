package com.example.shelfmate.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // utiliser comme switch entre l'émulateur et le WiFi
    //private static final String BASE_URL = "http://192.168.137.1:3000/";
    private static final String BASE_URL = "http://192.168.1.50:3000/";
    //private static final String BASE_URL = "http://10.O.2.2:3000/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // Précaution probablement superflue, ajoutée pour être sûr.e que les emodjis des tags s'affichent correctement.
            // Ceci dit, ces emodjis pourraient ne pas faire partie du tag, mais être affichés dans un composant graphique à part.
            // Configurer OkHttpClient avec UTF-8
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(chain -> {
                        // S'assurer que les requêtes utilisent UTF-8
                        var request = chain.request();
                        var newRequest = request.newBuilder()
                                .addHeader("Content-Type", "application/json; charset=utf-8")
                                .addHeader("Accept", "application/json; charset=utf-8")
                                .build();
                        return chain.proceed(newRequest);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}

