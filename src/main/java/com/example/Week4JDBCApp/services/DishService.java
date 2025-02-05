package com.example.Week4JDBCApp.services;

import com.example.Week4JDBCApp.models.Dish;
import com.example.Week4JDBCApp.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    //get all dishes from the repository

    public List<Dish> getAllDishes() {
        //business logic should have been here
        return DishRepository.getAllDishes();
    }
}
