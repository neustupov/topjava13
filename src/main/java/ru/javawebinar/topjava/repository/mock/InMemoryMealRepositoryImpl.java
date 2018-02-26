package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        if (repository.containsKey(id)) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = null;
        if (repository.get(id).getUserId() == userId) {
            meal = repository.get(id);
        }
        return meal;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll - sort by Time");
        return repository.values()
                .stream()
                .filter(x -> x.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getAllWithTimeAndDate(LocalTime startTime, LocalTime endTime,
                                                  LocalDate startDate, LocalDate endDate,
                                                  int idUser) {
        return getAll(idUser).stream().filter(x -> x.getTime().isAfter(startTime) && x.getTime().isBefore(endTime)
                && x.getDate().isAfter(startDate.minusDays(1)) && x.getDate().isBefore(endDate.plusDays(1))).collect(Collectors.toList());
    }
}

