package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDateTime;
import java.time.Month;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        MealRestController controller = appCtx.getBean("mealRestController", MealRestController.class);

        controller.create(new Meal(1, LocalDateTime.of(2020, Month.MAY, 2, 10, 0), "Перекус", 500));
        controller.create(new Meal(1, LocalDateTime.of(2020, Month.AUGUST, 19, 20, 0), "Ужин", 500));
        controller.create(new Meal(14, LocalDateTime.of(2020, Month.SEPTEMBER, 5, 20, 0), "Ужин", 410));
        controller.create(new Meal(1, LocalDateTime.of(2020, Month.MARCH, 31, 10, 0), "Завтрак", 1000));
        controller.create(new Meal(14, LocalDateTime.of(2020, Month.MAY, 2, 10, 0), "Завтрак", 500));

        controller.getAll().forEach(System.out::println);

        appCtx.close();
    }
}
