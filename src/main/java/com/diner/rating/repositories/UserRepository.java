package com.diner.rating.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.diner.rating.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {}