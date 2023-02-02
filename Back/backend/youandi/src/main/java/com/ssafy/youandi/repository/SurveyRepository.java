package com.ssafy.youandi.repository;

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

//    // DB에 모든 레코드 주문하기 때문에 데이터의 규모가 커지면 비용이 많이 듬
//    @Query(value = "select * from balancegame order by rand() limit 1", nativeQuery = true)
//    List<Balancegame> findAll();

    @Query(value = "select * from survey order by rand() limit 3", nativeQuery = true)
    List<Survey> findAll();

    long countBy();
    Page<Survey> findAll(Pageable pageable);
}
