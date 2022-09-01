package com.laioffer.tinnews.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//configure retrofit

//可以把retrofit理解为chrome
//chrome: google.com -> chrome -> call endpoint -> html, js -> render -> client webpage
//retrofit: endpoint("https...") -> retrofit -> json -> lib gson parsing -> java class
//retrofit use okHttpClient call endpoint
public class RetrofitClient {
    private static final String API_KEY = "432e49381f9f48f6a6c2533b885d71c5";
    private static final String BASE_URL = "https://newsapi.org/v2/";

    //singleton
    //using retrofit need a instance
    public static Retrofit newInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //json -> java class
                .client(okHttpClient) //http client to call endpoint
                .build();
    }

    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build();
            return chain.proceed(request);
        }
    }
}
