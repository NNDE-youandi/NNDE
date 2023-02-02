package com.ssafy.youandi.repository.game;

import com.ssafy.youandi.entity.game.balancegame.BalanceGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceGameRepository extends JpaRepository<BalanceGame, Integer> {
    long countBy();
    Page<BalanceGame> findAll(Pageable pageable);
}
