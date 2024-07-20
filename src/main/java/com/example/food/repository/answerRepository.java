package com.example.food.repository;

import com.example.food.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface answerRepository extends JpaRepository<Answer, Integer> {
}
