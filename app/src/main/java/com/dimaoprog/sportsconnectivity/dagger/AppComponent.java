package com.dimaoprog.sportsconnectivity.dagger;

import com.dimaoprog.sportsconnectivity.retrofit.IMealDB;
import com.dimaoprog.sportsconnectivity.retrofit.MealDBRetrofit;

import dagger.Component;

@Component(modules = MealDBRetrofit.class)
public interface AppComponent {
    IMealDB getMealDBApi();
}
