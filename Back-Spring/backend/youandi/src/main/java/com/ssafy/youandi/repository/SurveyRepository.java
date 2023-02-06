package com.ssafy.youandi.repository;

import com.ssafy.youandi.dto.response.SurveyResponseDto;
import com.ssafy.youandi.entity.game.balancegame.BalanceGame;
import com.ssafy.youandi.entity.user.survey.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    @Query(value = "select * from survey order by rand() limit 3", nativeQuery = true)
    List<Survey> findAll();

    long countBy();
    Page<Survey> findAll(Pageable pageable);

}
