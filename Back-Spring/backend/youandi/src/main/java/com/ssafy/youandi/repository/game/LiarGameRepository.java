package com.ssafy.youandi.repository.game;

import com.ssafy.youandi.entity.game.balancegame.BalanceGame;
import com.ssafy.youandi.entity.game.bombgame.BombGame;
import com.ssafy.youandi.entity.game.liargame.LiarGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiarGameRepository extends JpaRepository<LiarGame, Integer> {

    @Query("SELECT DISTINCT lgType FROM LiarGame ")
    List<String> findDistinctLgType();

    long countByLgType(String lgType);
    Page<LiarGame> findAllByLgType(Pageable pageable, String lgType);


}
