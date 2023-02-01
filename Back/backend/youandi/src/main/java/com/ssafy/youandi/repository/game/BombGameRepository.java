package com.ssafy.youandi.repository.game;

import com.ssafy.youandi.entity.game.bombgame.BombGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombGameRepository extends JpaRepository<BombGame, Integer> {

    long countBy();
    Page<BombGame> findAll(Pageable pageable);
}
