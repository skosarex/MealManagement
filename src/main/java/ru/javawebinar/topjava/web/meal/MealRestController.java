package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        log.info("MealRestController: getAll()");
        return MealsUtil.getTos(service.getAll(), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        if (startDate == null) startDate = LocalDate.MIN;
        if (startTime == null) startTime = LocalTime.MIN;
        if (endDate == null) endDate = LocalDate.MAX;
        if (endTime == null) endTime = LocalTime.MAX;
        log.info("MealRestController: getAll() with filters");
        return MealsUtil.getFilteredTos(service.getAll(), SecurityUtil.authUserCaloriesPerDay(), startDate, startTime, endDate, endTime);
    }

    public Meal get(int id) {
        log.info("MealRestController: get{}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("MealRestController: delete{}", id);
        service.delete(id);
    }

    public Meal create(Meal meal) {
        log.info("MealRestController: create{}");
        return service.create(meal);
    }

    public void update(Meal meal) {
        log.info("MealRestController: update{}");
        service.update(meal);
    }
}