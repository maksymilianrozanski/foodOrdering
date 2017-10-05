package com.example.food;

import com.example.food.Dao.DbAccess;
import com.example.food.model.Dish;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Dish.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());

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
                    String chosenCuisine = chosenCuisine(availableCuisines);
                    System.out.println("Chosen cuisine: " + chosenCuisine);
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

    private static String chosenCuisine(List<String> availableCuisines) {
        while (true) {
            System.out.println("List of available cuisines - please enter a number:");
            for (int i = 0; i < availableCuisines.size(); i++) {
                System.out.println(i + ") " + availableCuisines.get(i));
            }
            Scanner scanner = new Scanner(System.in);
            int chosenNumber;
            if (scanner.hasNextInt()){
                chosenNumber = scanner.nextInt();
            }else {
                chosenNumber = -1;
            }
            try {
                return availableCuisines.get(chosenNumber);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect number.");
            }
        }
    }
}
