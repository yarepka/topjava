package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Реализовать метод UserMealsUtil.getFilteredWithExceeded:
 * -  должны возвращаться только записи между startTime и endTime
 * -  поле UserMealWithExceed.exceed должно показывать,
 * превышает ли сумма калорий за весь день параметра метода caloriesPerDay
 * <p>
 * Т.е UserMealWithExceed - это запись одной еды, но поле exceeded будет одинаково для всех записей за этот день.
 */

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 1501),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> userMealWithExceeds =
                getFilteredWithExceeded(
                        mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        userMealWithExceeds.forEach(System.out::println);

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(
            List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        long currentTimeMillis = System.currentTimeMillis();

        List<UserMealWithExceed> userMealsWithExceed = new ArrayList<>();

        // группируем по дате (только те эл-ты поле dateTime которых удовлетворяет интервалу [startTime;endTime])
        Map<LocalDate, List<UserMeal>> personsByAge = mealList.stream()
                .filter(w -> TimeUtil.isBetween(w.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.groupingBy(u -> u.getDateTime().toLocalDate()));

        personsByAge.forEach((localDate, userMeal) -> {
            int caloriesSummary = (int)userMeal
                    .stream()
                    .collect(Collectors.summarizingInt(p -> p.getCalories())).getSum();

            boolean isExceed = caloriesSummary > caloriesPerDay;

            userMeal
                    .stream()
                    .forEach(u ->
                        userMealsWithExceed.add(
                                new UserMealWithExceed(
                                        u.getDateTime(),
                                        u.getDescription(),
                                        u.getCalories(),
                                        isExceed))
                    );
        });

        System.out.println("Working: " + (System.currentTimeMillis() - currentTimeMillis));

        return userMealsWithExceed;
    }
}
