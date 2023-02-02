package com.ssafy.youandi.repository;

import com.ssafy.youandi.entity.randomnick.RandomNick;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomNickRepository extends JpaRepository<RandomNick, Integer> {

    long countBy();
    Page<RandomNick> findAll(Pageable pageable);
}
