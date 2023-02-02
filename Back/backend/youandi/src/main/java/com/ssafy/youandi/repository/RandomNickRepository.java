package com.ssafy.youandi.repository;

import com.ssafy.youandi.entity.randomnick.RandomNick;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomNickRepository extends JpaRepository<RandomNick, Integer> {

    //    // DB에 모든 레코드 주문하기 때문에 데이터의 규모가 커지면 비용이 많이 듬
//    @Query(value = "select * from randomnick order by rand() limit 1", nativeQuery = true)
//    List<RandomNick> findAll();

    long countBy();
    Page<RandomNick> findAll(Pageable pageable);
}
