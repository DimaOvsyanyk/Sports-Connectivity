package com.dimaoprog.sportsconnectivity.dbEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealDBResponse {
    @SerializedName("meals")
    @Expose
    private List<MealDB> mealsDB = null;

    public List<MealDB> getMealsDB() {
        return mealsDB;
    }

    public void setMealsDB(List<MealDB> mealsDB) {
        this.mealsDB = mealsDB;
    }


}
