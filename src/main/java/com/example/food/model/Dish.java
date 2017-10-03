package com.example.food.model;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "dishname")
    private String dishName;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "typeofmeal")
    private String typeOfMeal;

    @Column(name = "price")
    private int price;

    public Dish() {}

    public Dish(String dishName, String cuisine, String typeOfMeal, int price) {
        this.dishName = dishName;
        this.cuisine = cuisine;
        this.typeOfMeal = typeOfMeal;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
