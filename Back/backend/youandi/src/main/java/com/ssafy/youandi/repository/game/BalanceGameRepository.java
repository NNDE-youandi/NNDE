package com.ssafy.youandi.repository.game;

import com.ssafy.youandi.entity.game.balancegame.BalanceGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceGameRepository extends JpaRepository<BalanceGame, Integer> {

//    // DB에 모든 레코드 주문하기 때문에 데이터의 규모가 커지면 비용이 많이 듬
//    @Query(value = "select * from balancegame order by rand() limit 1", nativeQuery = true)
//    List<Balancegame> findAll();

    long countBy();
    Page<BalanceGame> findAll(Pageable pageable);
}
