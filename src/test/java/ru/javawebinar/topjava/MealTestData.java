package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final Meal MEAL_OF_USER1 = new Meal(100003, "breakfast", 100);
    public static final Meal MEAL_OF_USER2 = new Meal(100004, "lunch", 500);
    public static final Meal MEAL_OF_ADMIN1 = new Meal(100005, "dinner", 800);

    public MealTestData() {
    }

    public static void assertMatch(Meal actual, Meal expected){
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}