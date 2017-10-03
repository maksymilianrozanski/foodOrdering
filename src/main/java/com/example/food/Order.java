package com.example.food;

import com.example.food.model.Dish;

public class Order {
    private Dish mainCourse;
    private Dish dessert;
    private Dish drink;

    public Order() {
    }

    public Order(Dish mainCourse, Dish dessert, Dish drink) {
        this.mainCourse = mainCourse;
        this.dessert = dessert;
        this.drink = drink;
    }

    public Dish getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(Dish mainCourse) {
        this.mainCourse = mainCourse;
    }

    public Dish getDessert() {
        return dessert;
    }

    public void setDessert(Dish dessert) {
        this.dessert = dessert;
    }

    public Dish getDrink() {
        return drink;
    }

    public void setDrink(Dish drink) {
        this.drink = drink;
    }
}
