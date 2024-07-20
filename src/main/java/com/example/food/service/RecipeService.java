package com.example.food.service;

import com.example.food.domain.entity.Recipe;
import com.example.food.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public void write(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public List<Recipe> recipeList(){

        return recipeRepository.findAll();
    }
}
