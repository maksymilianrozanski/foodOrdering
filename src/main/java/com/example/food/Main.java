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
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Enter 1 to order a drink, 2 to order a lunch.");
            int enteredNumber = scanner.nextInt();
            switch (enteredNumber) {
                case 1:
                    System.out.println("Entered number: " + enteredNumber);
                    System.out.println("Ordering a drink");
                    session.beginTransaction();
                    List<Dish> drinks = session.createQuery("FROM Dish D where D.typeOfMeal = 'drink'").list();
                    System.out.println("List of drinks");
                    for (Dish drink : drinks) {
                        System.out.println(drink.getDishName());
                    }
                    continueLoop = false;
                    break;
                case 2:
                    System.out.println("Entered number: " + enteredNumber);
                    System.out.println("Ordering a lunch");
                    continueLoop = false;
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

//    private static List<Dish> queryDrinks(SessionFactory factory) {
//        try {
//            Session session = factory.getCurrentSession();
//            String hql = "SELECT D FROM dishes where D.typeofmeal = drink";
//            Query query = session.createQuery(hql);
//            List<Dish> drinks = query.list();
//            return drinks;
//        } finally {
//            factory.close();
//        }
//    }
}
