package com.example.food.Dao;

import com.example.food.model.Dish;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DbAccess {

    public static List<Dish> queryDrinks(SessionFactory factory) {
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

    public static List<String> availableCuisines(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "FROM Dish D where D.typeOfMeal = 'mainCourse'";
            Query query = session.createQuery(hql);
            List<Dish> dishes = query.list();
            HashSet<String> cuisinesSet = new HashSet<>();
            for (Dish dish : dishes) {
                cuisinesSet.add(dish.getCuisine());
            }
            List<String> cuisinesList = new ArrayList<>();
            cuisinesList.addAll(cuisinesSet);
            return cuisinesList;
        } finally {
            session.close();
        }
    }

    public static List<Dish> mainCoursesWhereCuisine(SessionFactory factory, String cuisine) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "FROM Dish D where D.typeOfMeal = 'mainCourse' and D.cuisine = '" + cuisine + "'";
            Query query = session.createQuery(hql);
            List<Dish> mainCourses = query.list();
            return mainCourses;
        } finally {
            session.close();
        }
    }

    public static List<Dish> dessertWhereCuisine(SessionFactory factory, String cuisine) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "from Dish D where D.typeOfMeal = 'dessert' and D.cuisine = '" + cuisine + "'";
            Query query = session.createQuery(hql);
            List<Dish> desserts = query.list();
            return desserts;
        } finally {
            session.close();
        }
    }
}
