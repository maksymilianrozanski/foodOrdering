package com.example.food;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//TODO: catch java.util.InputMismatchException (if entered not number)
        while (true) {
            System.out.println("Enter 1 to order a drink, 2 to order a lunch.");
            int enteredNumber = scanner.nextInt();
            switch (enteredNumber) {
                case 1:
                    System.out.println("Entered number: " + enteredNumber);
                    System.out.println("Ordering a drink");
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

//        // create session factory
//        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Dish.class).buildSessionFactory();
//
//        Session session;

//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Dish dishFromDb = (Dish) session.get(Dish.class, 6);
//            System.out.println("Dish id: " + dishFromDb.getId() + " dishname: " + dishFromDb.getDishName());
//            session.getTransaction().commit();
//        } finally {
//            factory.close();
//        }
    }
}
