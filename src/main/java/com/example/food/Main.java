package com.example.food;

import com.example.food.model.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {


    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Dish.class)
                .buildSessionFactory();

        // create session
        Session session;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Dish dishFromDb = (Dish) session.get(Dish.class, 6);
            System.out.println("Dish id: " + dishFromDb.getId() + " dishname: " + dishFromDb.getDishName() );
            session.getTransaction().commit();
        }finally {
            factory.close();
        }
    }
}
