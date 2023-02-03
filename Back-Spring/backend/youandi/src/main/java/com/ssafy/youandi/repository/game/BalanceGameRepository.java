package com.ssafy.youandi.repository.game;

import com.ssafy.youandi.entity.game.balancegame.BalanceGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceGameRepository extends JpaRepository<BalanceGame, Integer> {
    @Query(value = "select * from balancegame order by rand() limit 3", nativeQuery = true)
    List<BalanceGame> findAll();

    long countBy();
    Page<BalanceGame> findAll(Pageable pageable);
}
