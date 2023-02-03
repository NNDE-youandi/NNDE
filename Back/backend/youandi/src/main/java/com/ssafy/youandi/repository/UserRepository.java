package com.ssafy.youandi.repository;

import com.ssafy.youandi.entity.mypageinfo.Record;
import com.ssafy.youandi.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmailAndProvider(String email,String provider);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);


}
