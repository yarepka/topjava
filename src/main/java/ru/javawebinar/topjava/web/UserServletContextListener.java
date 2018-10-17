package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.UserMealsUtil.getFilteredWithExceeded;

public class UserServletContextListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(UserServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.debug(">>>In UserServletContextListener");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

       /* List<UserMeal> mealList = Arrays.asList(

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510)
        );*/



        List<UserMeal> mealList = Arrays.asList(

                new UserMeal(LocalDateTime.parse("2015-05-30 10:00", formatter), "Breakfast", 500),
                new UserMeal(LocalDateTime.parse("2015-05-30 13:00", formatter), "Lunch", 1000),
                new UserMeal(LocalDateTime.parse("2015-05-30 20:00", formatter), "Dinner", 500),
                new UserMeal(LocalDateTime.parse("2015-05-31 10:00", formatter), "Breakfast", 1000),
                new UserMeal(LocalDateTime.parse("2015-05-31 13:00", formatter), "Lunch", 500),
                new UserMeal(LocalDateTime.parse("2015-05-31 20:00", formatter), "Dinner", 510)
        );


        //mealList.stream().map(um -> um.getDateTime().format(formatter))

        List<UserMealWithExceed> userMealWithExceeds =
                UserMealsUtil.getWithExceeded(mealList, 2000);

        sce.getServletContext().setAttribute("userMeals", userMealWithExceeds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
