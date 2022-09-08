package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

//    {
//        MealsUtil.MEALS.forEach(this::save);
//    }

    @Override
    public Meal save(Meal meal) {
        try {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                repository.put(meal.getId(), meal);
                return meal;
            }
            // treat case: update, but not present in storage
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        try {
            if (repository.get(id).getUserId() != userId)
                return false;
            return repository.remove(id) != null;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        try {
            Meal meal = repository.get(id);
            if (meal.getUserId() != userId)
                return null;
            return meal;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        try {
            List<Meal> userMeals = new ArrayList<>();
            userMeals = repository.values()
                    .stream()
                    .filter(m -> m.getUserId() == userId)
                    .sorted((o1, o2) -> {
                        if (o2.getDateTime().isAfter(o1.getDateTime())) {
                            return 1;
                        } else if (o2.getDateTime().isBefore(o1.getDateTime())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    })
                    .collect(Collectors.toList());
            if (userMeals.isEmpty())
                return Collections.emptyList();
            return userMeals;
        } catch (NullPointerException e) {
            return Collections.emptyList();
        }
    }


    // test
    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(14, LocalDateTime.of(2020, Month.MAY, 2, 10, 0), "Завтрак", 500),
                new Meal(14, LocalDateTime.of(2020, Month.NOVEMBER, 15, 13, 0), "Обед", 1000),
                new Meal(14, LocalDateTime.of(2020, Month.AUGUST, 19, 20, 0), "Ужин", 500),
                new Meal(14, LocalDateTime.of(2020, Month.JANUARY, 2, 10, 19), "Еда на граничное значение", 100),
                new Meal(14, LocalDateTime.of(2020, Month.MARCH, 31, 10, 0), "Завтрак", 1000),
                new Meal(14, LocalDateTime.of(2020, Month.JANUARY, 2, 13, 0), "Обед", 500),
                new Meal(14, LocalDateTime.of(2020, Month.SEPTEMBER, 5, 20, 0), "Ужин", 410)
        );
        InMemoryMealRepository repo = new InMemoryMealRepository();
        meals.forEach(repo::save);
        repo.getAll(14).forEach(System.out::println);

    }
}

