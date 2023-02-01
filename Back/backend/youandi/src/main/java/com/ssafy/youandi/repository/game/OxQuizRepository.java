package com.ssafy.youandi.repository.game;

import com.ssafy.youandi.entity.game.oxquiz.OxQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OxQuizRepository extends JpaRepository<OxQuiz, Integer> {

    long countBy();
    Page<OxQuiz> findAll(Pageable pageable);
}
