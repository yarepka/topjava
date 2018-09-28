package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(
            List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        // лист тех объектов поле dateTime, которых удовлетворят
        // интервалу [startTime;endTime]

        mealList = mealList.stream()
                .filter(w -> TimeUtil.isBetween(w.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());


        // нужно посчитать сумму каллорий всех приёмов пищи за день,
        // и чтобы эта сумма была меньше caloriesPerDay

        // если кол-во калорий привышает caloriesPerDay, то
        // для всех приёмов пищи данного дня - exceed = false.



        return null;
    }
}
