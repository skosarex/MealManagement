package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal);

    // false if not found or userId != meal.userId
    boolean delete(int id, int userId);

    // null if not found or userId != meal.userId
    Meal get(int id, int userId);

    //null if userId != meal.userId
    Collection<Meal> getAll(int userId);
}
