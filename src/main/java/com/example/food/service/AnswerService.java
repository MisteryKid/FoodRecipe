package com.example.food.service;


import com.example.food.domain.entity.Answer;
import com.example.food.domain.entity.Food;
import com.example.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.food.domain.entity.QFood.food;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final FoodRepository foodRepository;
    private final com.example.food.repository.answerRepository answerRepository;


    public void create(String content, Integer foodId){
        //Food food = foodRepository.findById(foodId);
        Food food = foodRepository.findById(foodId).orElse(null);
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setFood(food);
        this.answerRepository.save(answer);
    }
}
