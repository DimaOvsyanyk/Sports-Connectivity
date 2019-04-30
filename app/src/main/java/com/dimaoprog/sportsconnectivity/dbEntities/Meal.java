package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "meal", foreignKeys = @ForeignKey(entity = DailyMenu.class, parentColumns = "id",
childColumns = "daily_menu_id", onDelete = ForeignKey.CASCADE))
public class Meal {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "daily_menu_id", index = true)
    private long dailyMenuId;

    @ColumnInfo(name = "food_intake")
    private String foodIntake;

    private String meal;

    public Meal(long dailyMenuId, String foodIntake, String meal) {
        this.dailyMenuId = dailyMenuId;
        this.foodIntake = foodIntake;
        this.meal = meal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDailyMenuId() {
        return dailyMenuId;
    }

    public void setDailyMenuId(long dailyMenuId) {
        this.dailyMenuId = dailyMenuId;
    }

    public String getFoodIntake() {
        return foodIntake;
    }

    public void setFoodIntake(String foodIntake) {
        this.foodIntake = foodIntake;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
}
