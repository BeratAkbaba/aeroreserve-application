package com.myapp.user_service.mapper;



import org.mapstruct.Mapper;

import com.myapp.user_service.dto.WalletResponseDto;
import com.myapp.user_service.entity.WalletEntity;

@Mapper(componentModel = "spring")
public interface WalletMapper {

	WalletResponseDto toResponseDto(WalletEntity entity);
}
