package com.dimaoprog.sportsconnectivity.receiptViews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.dimaoprog.sportsconnectivity.dagger.DaggerAppComponent;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDB;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBResponse;
import com.dimaoprog.sportsconnectivity.retrofit.MealDBRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiptAddNewViewModel extends AndroidViewModel {

    private List<MealDB> mealDBList = new ArrayList<>();
    private MutableLiveData<List<MealDB>> mealDBLiveList = new MutableLiveData<>();

    public ReceiptAddNewViewModel(@NonNull Application application) {
        super(application);
    }

    public List<MealDB> getMealDBList() {
        return mealDBList;
    }

    public void setMealDBList(List<MealDB> mealDBList) {
        this.mealDBList = mealDBList;
        setMealDBLiveList();
    }

    public MutableLiveData<List<MealDB>> getMealDBLiveList() {
        return mealDBLiveList;
    }

    public void setMealDBLiveList() {
        mealDBLiveList.setValue(mealDBList);
    }

    public void setNewListByCategory(String category) {
        DaggerAppComponent.create().getMealDBApi().getListByCategory(category).enqueue(new Callback<MealDBResponse>() {
            @Override
            public void onResponse(Call<MealDBResponse> call, Response<MealDBResponse> response) {
                setMealDBList(response.body().getMealsDB());
            }

            @Override
            public void onFailure(Call<MealDBResponse> call, Throwable t) {

            }
        });
    }
}
