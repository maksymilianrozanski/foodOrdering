package com.example.food;

import com.example.food.Dao.DbAccess;
import com.example.food.model.Dish;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class Order {
    private Dish mainCourse;
    private Dish dessert;
    private Dish drink;
    private boolean iceCubes;
    private boolean lemon;

    Order() {
    }

    public Order(Dish mainCourse, Dish dessert, Dish drink) {
        this.mainCourse = mainCourse;
        this.dessert = dessert;
        this.drink = drink;
    }

    private Dish getMainCourse() {
        return mainCourse;
    }

    private void setMainCourse(Dish mainCourse) {
        this.mainCourse = mainCourse;
    }

    private Dish getDessert() {
        return dessert;
    }

    private void setDessert(Dish dessert) {
        this.dessert = dessert;
    }

    private Dish getDrink() {
        return drink;
    }

    private void setDrink(Dish drink) {
        this.drink = drink;
    }

    private boolean isIceCubes() {
        return iceCubes;
    }

    private void setIceCubes(boolean iceCubes) {
        this.iceCubes = iceCubes;
    }

    private boolean isLemon() {
        return lemon;
    }

    private void setLemon(boolean lemon) {
        this.lemon = lemon;
    }

    void chooseMainCourse(List<Dish> mainCourses) {
        while (this.getMainCourse() == null) {
            System.out.println("Available main courses - please enter a number:");
            for (int i = 0; i < mainCourses.size(); i++) {
                System.out.println(i + ") " + mainCourses.get(i).getDishName() + ", price: " + mainCourses.get(i).getPrice() + "$");
            }
            Scanner scanner = new Scanner(System.in);
            int chosenNumber;
            if (scanner.hasNextInt()) {
                chosenNumber = scanner.nextInt();
            } else {
                chosenNumber = -1;
            }
            try {
                this.setMainCourse(mainCourses.get(chosenNumber));
                System.out.println("Added " + mainCourses.get(chosenNumber).getDishName() + " to your order.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect number.");
            }
        }
    }

    void chooseDessert(List<Dish> desserts) {
        System.out.println("Available desserts - please enter a number:");
        for (int i = 0; i < desserts.size(); i++) {
            System.out.println(i + ") " + desserts.get(i).getDishName() + ", price " + desserts.get(i).getPrice() + "$");
        }
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = scanner.nextInt();
        try {
            this.setDessert(desserts.get(chosenNumber));
            System.out.println("Added " + desserts.get(chosenNumber).getDishName() + " to your order.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect number.");
        }
    }

    void chooseDrink(SessionFactory factory) {
        Scanner scanner = new Scanner(System.in);
        List<Dish> drinks = DbAccess.queryDrinks(factory);
        System.out.println("List of drinks:");
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println(i + ") " + drinks.get(i).getDishName() + ", price: " + drinks.get(i).getPrice() + "$");
        }
        System.out.println("Enter the number of drink to order.");

        int enteredNumber = scanner.nextInt();
        System.out.println("Entered number: " + enteredNumber);

        try {
            Dish orderedDrink = drinks.get(enteredNumber);
            this.setDrink(orderedDrink);
            this.askForIceAndLemon();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect number.");
        }
    }

    private void askForIceAndLemon() {
        System.out.println("Should we add ice cubes or lemon to ordered drink?");
        System.out.println("Enter:\n0) no ice cubes, no lemon\n1) add only ice cubes\n2) add only lemon\n3) add ice cubes and lemon");
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = scanner.nextInt();
        switch (chosenNumber) {
            case 0:
                break;
            case 1:
                this.setIceCubes(true);
                break;
            case 2:
                this.setLemon(true);
                break;
            case 3:
                this.setIceCubes(true);
                this.setLemon(true);
                break;
            default:
                System.out.println("Incorrect number");
                break;
        }
    }

    void printOrderSummary() {
        System.out.println("Your order summary:");
        int totalPrice = 0;
        if (this.drink != null) {
            System.out.println("Drink: " + this.getDrink().getDishName() + ", price: " + this.getDrink().getPrice() + "$");
            if (this.isIceCubes()) {
                System.out.println("+Extra Ice cubes");
            }
            if (this.isLemon()) {
                System.out.println("+Extra Lemon");
            }
            totalPrice = totalPrice + this.getDrink().getPrice();
        }
        if (this.mainCourse != null) {
            System.out.println("Main course: " + this.getMainCourse().getDishName() + ", price: " + this.getMainCourse().getPrice() + "$");
            totalPrice = totalPrice + this.getMainCourse().getPrice();
        }
        if (this.dessert != null) {
            System.out.println("Dessert: " + this.getDessert().getDishName() + ", price: " + this.getDessert().getPrice() + "$");
            totalPrice = totalPrice + this.getDessert().getPrice();
        }
        System.out.println("Total price: " + totalPrice + "$");
    }
}
