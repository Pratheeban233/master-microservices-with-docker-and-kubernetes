package com.tutorial.userapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRespository extends JpaRepository<UserJpa,Integer> {
}
