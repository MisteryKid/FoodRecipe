package com.example.food.service;

import com.example.food.domain.entity.Food;
import com.example.food.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public void write(Food food){

        foodRepository.save(food);
    }

    public Page<Food> foodList(Pageable pageable){

         return foodRepository.findAll(pageable);
    }

    public Food listView(Integer id){
        //return boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return foodRepository.findById(id).get();
    }



}
