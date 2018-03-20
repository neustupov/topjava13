package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    Optional<Meal> findByIdAndUser_id(Integer id, int userId);

    List<Meal> findAllByUser_idOrderByIdDesc(Integer userId);

    List<Meal> findMealByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);

    @Transactional
    int deleteMealByIdAndUser_id(int id, int userId);

    Meal save(Meal Meal);
}
