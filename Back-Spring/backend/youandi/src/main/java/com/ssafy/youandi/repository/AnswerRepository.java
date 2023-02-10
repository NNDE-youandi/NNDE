package com.ssafy.youandi.repository;

import com.ssafy.youandi.entity.user.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    List<Answer> findByUser_UserId(long userId);

}
