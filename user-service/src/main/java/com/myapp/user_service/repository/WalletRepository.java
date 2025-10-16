package com.myapp.user_service.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.myapp.user_service.entity.WalletEntity;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long>{

}
