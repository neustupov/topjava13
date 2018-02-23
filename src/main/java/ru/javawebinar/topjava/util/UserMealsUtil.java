package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
        Map<LocalDate, Integer> map = new HashMap<>();
        if (!mealList.isEmpty()) {
            for (UserMeal userMeal : mealList) {
                map.put(userMeal.getDateTime().toLocalDate(), map.getOrDefault(userMeal.getDateTime().toLocalDate(), 0) + userMeal.getCalories());
            }
        }

        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                                map.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay
                        )
                );
            }
        }
        return userMealWithExceedList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStreams(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();
        mealList.forEach(um -> map.put(um.getDateTime().toLocalDate(), map.getOrDefault(um.getDateTime().toLocalDate(), 0) + um.getCalories()));

        return mealList.stream()
                .filter(x -> TimeUtil.isBetween(x.getDateTime().toLocalTime(), startTime, endTime))
                .map(temp -> new UserMealWithExceed(temp.getDateTime(), temp.getDescription(), temp.getCalories(),
                        map.get(temp.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
