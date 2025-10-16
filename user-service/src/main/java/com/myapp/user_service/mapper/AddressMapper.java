package com.myapp.user_service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.myapp.user_service.dto.AddressRequestDto;
import com.myapp.user_service.dto.AddressResponseDto;
import com.myapp.user_service.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true) 
    AddressEntity toEntity(AddressRequestDto dto);
	
	AddressResponseDto toResponseDto(AddressEntity entity);
	
	List<AddressResponseDto> toResponseDtoList(List<AddressEntity> entities);
}
