package com.myapp.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.user_service.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

	Optional<AddressEntity> findByIdAndUser_Id(Long addressId, Long userId);
}
