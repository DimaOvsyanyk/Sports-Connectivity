package com.dimaoprog.sportsconnectivity.dbEntities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "daily_menu", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id",
        childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class DailyMenu {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_id", index = true)
    private long userId;

    @ColumnInfo(name = "menu_title")
    private String menuTitle;

    @ColumnInfo(name = "date_of_menu")
    private String dateOfMenu;

    @Ignore
    private List<Meal> meals;

    public DailyMenu(long userId, String menuTitle, String dateOfMenu) {
        this.userId = userId;
        this.menuTitle = menuTitle;
        this.dateOfMenu = dateOfMenu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getDateOfMenu() {
        return dateOfMenu;
    }

    public void setDateOfMenu(String dateOfMenu) {
        this.dateOfMenu = dateOfMenu;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
