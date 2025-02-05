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
        //validate the data
        if(dish.getPrice() > 200){
            model.addAttribute("message", "Price should be less than 200");
            return "add-dish";
        }
        //save the data
        //open the menu page with updates data
        model.addAttribute("dishes", dish);
        model.addAttribute("message", dish.getName() + " added successfully");
        return "menu";
    }


}
