package com.example.Week4JDBCApp.services;

import com.example.Week4JDBCApp.models.Dish;
import com.example.Week4JDBCApp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    //injecting the repository
    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        //business logic should have been here
        return dishRepository.getAllDishes();
    }

    public int saveDish(Dish dish) {
        //business logic should have been here
        if(dish.getPrice() > 20){
            return 0;
        }
        dishRepository.saveDish(dish);
        return 1;
//        return dishRepository.saveDish(dish);
    }
}
