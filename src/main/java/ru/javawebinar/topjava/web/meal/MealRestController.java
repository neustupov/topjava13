package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;


@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal){
        log.info("save {}", meal);
        service.save(meal);
        return meal;
    }

    public void delete(int id) throws NotFoundException {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Meal get(int id, int userId) throws NotFoundException{
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public Collection<MealWithExceed> getAll(int userId){
        log.info("getAll {}");
        return service.getAll(userId);
    }

    public Collection<Meal> getAllWithTimeAndDate(LocalTime startTime, LocalTime endTime,
                                                  LocalDate startDate, LocalDate endDate, int idUser) {
        return service.getAllWithTimeAndDate(startTime, endTime, startDate, endDate, idUser);
    }
}