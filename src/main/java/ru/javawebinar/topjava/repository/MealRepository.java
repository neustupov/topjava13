package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getAllWithTimeAndDate(LocalDateTime startDate, LocalDateTime startTime,
                                           LocalDateTime endDate, LocalDateTime endTime);
}
