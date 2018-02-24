package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface MealDao {

    void add(Meal meal);
    void edit(Meal meal);
    void delete(int id);
    Meal getMeal(int id);
    List getAllMeals();
    AtomicInteger getCounter();

}
