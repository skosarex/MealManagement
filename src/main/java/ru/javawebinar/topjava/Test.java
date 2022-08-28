package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.util.MealsUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        MealDao mealDao = new MealDao();
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 23, 9, 37), "Breakfast", 550),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 23, 13, 55), "Lunch", 450),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 23, 21, 44), "Dinner", 1100),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 24, 12, 0), "Breakfast", 570),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 24, 21, 15), "Dinner", 890),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 25, 12, 14), "Breakfast", 600),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 25, 16, 0), "Lunch", 500),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 25, 23, 7), "Dinner", 1200),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 26, 13, 24), "Breakfast", 600),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 26, 20, 5), "Dinner", 700),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 27, 9, 56), "Breakfast", 300),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 27, 15, 7), "Lunch", 520),
                new Meal(LocalDateTime.of(2022, Month.AUGUST, 27, 22, 30), "Dinner", 800)
        );
        List<MealTo> mealToList = MealsUtil.getFiltered(meals, LocalTime.MIN, LocalTime.MAX, MealsUtil.DEFAULT_CALORIES_PER_DAY);

        for (MealTo meal : mealToList) {
            mealDao.add(meal);
        }

//        mealDao.update(new MealTo(3, LocalDateTime.now(), "new description!!!", 1000000000, true));
//        System.out.println(mealDao.get(3));

        //System.out.println(mealDao.get(0).getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
