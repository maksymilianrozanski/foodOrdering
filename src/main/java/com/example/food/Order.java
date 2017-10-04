package com.example.food;

import com.example.food.model.Dish;

import java.util.List;
import java.util.Scanner;

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

    public void chooseMainCourse(List<Dish> mainCourses) {
        System.out.println("Available main courses - please enter a number:");
        for (int i = 0; i < mainCourses.size(); i++) {
            System.out.println(i + ") " + mainCourses.get(i).getDishName() + ", price: " + mainCourses.get(i).getPrice() + "$");
        }
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = scanner.nextInt();
        try {
            this.setMainCourse(mainCourses.get(chosenNumber));
            System.out.println("Added " + mainCourses.get(chosenNumber).getDishName() + " to your order.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect number.");
        }
    }

    public void chooseDessert(List<Dish> desserts) {
        System.out.println("Available desserts - please enter a number:");
        for (int i = 0; i < desserts.size(); i++) {
            System.out.println(i + ") " + desserts.get(i).getDishName() + ", price " + desserts.get(i).getPrice() + "$");
        }
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = scanner.nextInt();
        try {
            this.setDessert(desserts.get(chosenNumber));
            System.out.println("Added " + desserts.get(chosenNumber).getDishName() + " to your order.");
        }catch (IndexOutOfBoundsException e){
            System.out.println("Incorrect number.");
        }
    }

    public void printOrderSummary(){
        System.out.println("Your order summary:");
        int totalPrice = 0;
        if (this.mainCourse != null){
            System.out.println("Main course: " + this.getMainCourse().getDishName()
                    + ", price: " + this.getMainCourse().getPrice() + "$");
            totalPrice = totalPrice + this.getMainCourse().getPrice();
        }
        if (this.dessert != null){
            System.out.println("Dessert: " + this.getDessert().getDishName()
            + ", price: " + this.getDessert().getPrice() + "$");
            totalPrice = totalPrice + this.getDessert().getPrice();
        }
        System.out.println("Total price: " + totalPrice + "$");
    }
}
