package com.example.Week4JDBCApp.repositories;

import com.example.Week4JDBCApp.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DishRepository {

    @Autowired
    private JdbcTemplate template;

    //get all dishes from the db
    public List<Dish> getAllDishes(){

        String sql = "SELECT * FROM dishes";
//        mapper: RowMapper<Dish> のインスタンスで、ResultSet の各行を Dish オブジェクトに
//        変換するためのマッピングロジックを提供します。
        RowMapper<Dish> mapper = new RowMapper<Dish>(){

            @Override
            public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
                Dish myDish = new Dish();
                myDish.setId(rs.getInt("id"));
                myDish.setName(rs.getString("name"));
                myDish.setCategory(rs.getString("category"));
                myDish.setPrice(rs.getDouble("price"));

                return myDish;
            }
        };

        //1st argument = sql
        //2nd argument = row mapper

        //このコードは、JdbcTemplate を使用してデータベースからデータを取得し、
        // Dish オブジェクトのリストを返しています。
        // 具体的には、template.query(sql, mapper) メソッドを使用して、
        // SQL クエリを実行し、その結果を RowMapper を使用して Dish オブジェクトにマッピングしています。
        List<Dish> dishes =  template.query(sql, mapper);
        return dishes;

    }

    //save a dish to the db
    public int saveDish(Dish dish){
        String sql = "INSERT INTO dishes (name, category, price) VALUES (?, ?, ?)";
        return template.update(sql, dish.getName(), dish.getCategory(), dish.getPrice());
    }

}
