package com.myapp.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.myapp.user_service.dto.UserRequestDto;
import com.myapp.user_service.dto.UserResponseDto;
import com.myapp.user_service.entity.UserEntity;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {

	
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "wallet", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(UserRequestDto dto);


    @Mapping(source = "createdAt", target = "memberSince")
    UserResponseDto toResponseDto(UserEntity entity);
    
    
}
