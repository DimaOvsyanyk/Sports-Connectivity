package com.dimaoprog.sportsconnectivity.retrofit;

import com.dimaoprog.sportsconnectivity.dbEntities.MealDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMealDB {

    @GET("filter.php")
    Call<MealDBResponse> getListByCategory(@Query("c") String category);

    @GET("lookup.php")
    Call<MealDBResponse> getMealById(@Query("i") String id);
}
