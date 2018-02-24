package ru.javawebinar.topjava.dao.daoImpl;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImplInMemory implements MealDao{

    private static List<Meal> ourInstance = new CopyOnWriteArrayList<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    private static List<Meal> getInstance() {
        if (ourInstance == null) {
            ourInstance = new CopyOnWriteArrayList<>();
            return ourInstance;
        }
        return ourInstance;
    }

    public MealDaoImplInMemory() {
    }

    static void incrementCounter(){
        counter.getAndIncrement();
    }

    static void decrementCounter(){
        counter.decrementAndGet();
    }

    @Override
    public void add(Meal meal) {
        getInstance().add(meal);
        incrementCounter();
    }

    @Override
    public void edit(Meal meal) {
        for (Meal oneMeal : getInstance()) {
            if (oneMeal.getId() == meal.getId()) {
                getInstance().add(getInstance().indexOf(oneMeal), meal);
                getInstance().remove(oneMeal);
            }
        }
    }

    @Override
    public void delete(int id) {
        getInstance().remove(getMeal(id));
    }

    @Override
    public Meal getMeal(int id) {
        Meal meal = null;
        for (Meal oneMeal : getInstance()) {
            if (oneMeal.getId() == id) {
                meal = oneMeal;
            }
        }
        return meal;
    }

    @Override
    public List getAllMeals() {
        return MealsUtil.createWithExceedWithoutTime(getInstance(), 2000);
    }

    @Override
    public AtomicInteger getCounter() {
        return counter;
    }
}
