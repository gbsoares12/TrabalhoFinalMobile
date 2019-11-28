package com.example.trabalhodsd.service;

import com.example.trabalhodsd.model.RequestTarefa;
import com.example.trabalhodsd.model.Tarefa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface CalendarService {

    @GET("{id}/json")
    Call<Tarefa> select(@Path("id") int id);

    @Headers({"Accept: application/json"})
    @POST("calendarEnvio")
    Call<List<Tarefa>> postJson(@Body RequestTarefa urlMoodle);

}
