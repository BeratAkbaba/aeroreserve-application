package com.myapp.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.user_service.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	boolean existsByEmail(String email);
	
	Optional<UserEntity> findByEmail(String email);
}
