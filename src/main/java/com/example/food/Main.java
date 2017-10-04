package com.example.food;

import com.example.food.model.Dish;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Dish.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        Scanner scanner = new Scanner(System.in);
//TODO: catch java.util.InputMismatchException (if entered not number)
        while (true) {
            System.out.println("Enter 1 to order a drink, 2 to order a lunch.");
            int enteredNumber = scanner.nextInt();
            switch (enteredNumber) {
                case 1:
                    orderDrink(factory);
                    break;
                case 2:
                    System.out.println("Entered number: " + enteredNumber);
                    System.out.println("Ordering a lunch");
                    break;
                default:
                    System.out.println("Incorrect input, Enter 1 to order a drink, 2 to order a lunch.");
                    break;
            }
        }

//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Dish dishFromDb = (Dish) session.get(Dish.class, 6);
//            System.out.println("Dish id: " + dishFromDb.getId() + " dishname: " + dishFromDb.getDishName());
//            session.getTransaction().commit();
//        } finally {
//            factory.close();
//        }
//    }
    }

    private static List<Dish> queryDrinks(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "FROM Dish D where D.typeOfMeal = 'drink'";
            Query query = session.createQuery(hql);
            List<Dish> drinks = query.list();
            return drinks;
        } finally {
            session.close();
        }
    }

    private static void orderDrink(SessionFactory factory){
        int totalPrice = 0;
        Scanner scanner = new Scanner(System.in);
        List<Dish> drinks = queryDrinks(factory);
        System.out.println("List of drinks:");
        for (int i = 0; i < drinks.size(); i++) {
            System.out.println(i + ") " + drinks.get(i).getDishName() + ", price: " + drinks.get(i).getPrice() + "$");
        }
        System.out.println("Enter the number of drink to order.");

        int enteredNumber = scanner.nextInt();
        System.out.println("Entered number: " + enteredNumber);

        try {
            Dish orderedDrink = drinks.get(enteredNumber);
            totalPrice = totalPrice + orderedDrink.getPrice();
            System.out.println("Your order: " + orderedDrink.getDishName() +", price: "+ totalPrice + "$");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect number. ");
        }
    }
}
