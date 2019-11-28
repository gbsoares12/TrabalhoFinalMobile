package com.example.trabalhodsd.retrofit;

import com.example.trabalhodsd.service.CalendarService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitializer {
    private final Retrofit retrofit;

    public RetrofitInitializer() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();


        retrofit = new Retrofit.Builder().baseUrl("https://faculzap-api.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public CalendarService getTarefas() {
        return retrofit.create(CalendarService.class);
    }
}


