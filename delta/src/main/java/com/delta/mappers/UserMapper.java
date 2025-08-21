package com.delta.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.delta.dtos.UserRequestDTO;
import com.delta.dtos.UserResponseDTO;
import com.delta.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
	
	User toEntity(UserRequestDTO dto);
	List<UserResponseDTO> toDTOList(List<User> users);
	UserResponseDTO toDTO(User user);
	
//	private UserMapper() {}
//	
//	public static User toEntity(UserRequestDTO dto) {
//        User u = new User();
//        u.setName(dto.getName());
//        u.setEmail(dto.getEmail());
//        u.setPassword(dto.getPassword());
//        u.setRole(dto.getRole());
//        
//        return u;
//    }
//	public static UserResponseDTO toResponseDTO(User user) {
//		UserResponseDTO u = new UserResponseDTO();
//		u.setId(user.getId());
//        u.setName(user.getName());
//        u.setEmail(user.getEmail());
//        u.setRole(user.getRole());
//        u.setCreatedAt(user.getCreatedAt());
//        u.setUpdatedAt(user.getUpdatedAt());
//        
//        return u;
//    }
}
