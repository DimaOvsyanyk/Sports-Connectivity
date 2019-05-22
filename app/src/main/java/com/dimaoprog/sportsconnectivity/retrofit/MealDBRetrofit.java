package com.dimaoprog.sportsconnectivity.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MealDBRetrofit {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @Provides
    public IMealDB getApi(Retrofit retrofit) {
        return retrofit.create(IMealDB.class);
    }

    @Provides
    public Retrofit getRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    public Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    public HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
