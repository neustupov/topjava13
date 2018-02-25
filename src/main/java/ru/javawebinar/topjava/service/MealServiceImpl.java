package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    public MealServiceImpl() {
    }

    @Override
    public Meal save(Meal meal) {
        repository.save(meal);
        return meal;
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(
                repository.get(id, userId), id);
    }

    @Override
    public Collection<MealWithExceed> getAll(int userId) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), 2000);
    }

    @Override
    public Collection<Meal> getAllWithTimeAndDate(LocalDateTime startDate, LocalDateTime startTime, LocalDateTime endDate, LocalDateTime endTime) {
        return null;
    }

}