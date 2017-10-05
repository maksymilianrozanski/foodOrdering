package com.example.food;

import com.example.food.Dao.DbAccess;
import com.example.food.model.Dish;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Dish.class).buildSessionFactory();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 1 to order a drink, 2 to order a lunch.");
            Order order = new Order();
            int enteredNumber;
            if (scanner.hasNextInt()) {
                enteredNumber = scanner.nextInt();
            } else {
                enteredNumber = -1;
                scanner.next();
            }
            switch (enteredNumber) {
                case 1:
                    order.chooseDrink(factory);
                    order.printOrderSummary();
                    break;
                case 2:
                    System.out.println("Entered number: " + enteredNumber);
                    System.out.println("Ordering a lunch");
                    //must choose cuisine first - query for possible cuisines
                    List<String> availableCuisines = DbAccess.availableCuisines(factory);
                    //choose a cuisine
                    String chosenCuisine;
                    try {
                        chosenCuisine = DbAccess.chosenCuisine(availableCuisines);
                        System.out.println("Chosen cuisine: " + chosenCuisine);
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                    //choose main course
                    List<Dish> mainCourses = DbAccess.mainCoursesWhereCuisine(factory, chosenCuisine);
                    order.chooseMainCourse(mainCourses);
                    //choose dessert
                    List<Dish> desserts = DbAccess.dessertWhereCuisine(factory, chosenCuisine);
                    order.chooseDessert(desserts);
                    order.printOrderSummary();
                    break;
                default:
                    System.out.println("Incorrect input.");
                    break;
            }
        }
    }
}
