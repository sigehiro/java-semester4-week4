package com.example.Week4JDBCApp.controllers;

import com.example.Week4JDBCApp.models.Dish;
import com.example.Week4JDBCApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
//localhost:8080/restaurant/home とかmenuにアクセスすることができる
public class DishController {

    @Autowired
    private DishService dishService;

    @Value("${restaurant.name}")
    private String restaurantName;

    //endpoint for home page
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("rName", restaurantName);
        return "home";
    }

    //endpoint for main page
    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        return "menu";
    }

    //endpoint for add dish page
    @GetMapping("/add")
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        return "add-dish";
    }

    //endpoint the save the dish
    @PostMapping("/save")
    public String saveDishes(@ModelAttribute Dish dish, Model model){

        int statusCode = dishService.saveDish(dish);
        if(statusCode == 0){
            model.addAttribute("message", "Price should be less than 20");
            return "add-dish";
        }
        //http://localhost:8080/restaurant/save
        //Data is input anything in add-dish -> solve <problem -> ID:0 in save page> -> auto increment
        List<Dish> dishes = dishService.getAllDishes();
        Dish lastDishes = dishes.get(dishes.size() - 1);

        //save the data
        //open the menu page with updates data
        model.addAttribute("dishes", lastDishes);
        model.addAttribute("message", lastDishes.getName() + " added successfully");
        return "menu";
    }


}
