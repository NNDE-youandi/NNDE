package com.ssafy.youandi.repository;
import com.ssafy.youandi.entity.keyword.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

    long countBy();
    Page<Keyword> findAll(Pageable pageable);

}
