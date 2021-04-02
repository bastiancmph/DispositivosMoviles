package com.example.whatyouseenow;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiagnosticVetApiAdapter {
    private static DiagnosticVetApiService API_SERVICE;

    public static DiagnosticVetApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl = "\n" +
                "https://api.themoviedb.org/3/trending/movie/day?api_key=a804b0da37710e733d1d87cf53bb816b";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(DiagnosticVetApiService.class);
        }

        return API_SERVICE;
    }
}
