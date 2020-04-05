package com.example.login.services;

import com.example.login.models.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Client {

    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> getReposForUser(@Path("user") String user);

}
