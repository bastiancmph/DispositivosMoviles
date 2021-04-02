package com.example.whatyouseenow;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiagnosticVetApiService {

    @GET("movie/top_rated?api_key=a804b0da37710e733d1d87cf53bb816b&language=en-US&page=1")
    Call<PeliculasResponse> getPeliculasResponse();
}
