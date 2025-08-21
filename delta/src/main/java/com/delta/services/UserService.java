package com.delta.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delta.dtos.UserRequestDTO;
import com.delta.dtos.UserResponseDTO;
import com.delta.entities.User;
import com.delta.exceptions.UserNotFoundException;
import com.delta.mappers.UserMapper;
import com.delta.repos.UserRepository;

@Service
public class UserService {

	
	private final UserRepository uRepo;
	private final UserMapper mapper;

    
    public UserService(UserRepository uRepo,UserMapper mapper) {
        this.uRepo = uRepo;
        this.mapper=mapper;
    }
    
	
	
	public UserResponseDTO registerUser(UserRequestDTO dto) {
		//if(user.getEmail()==null) throw new RuntimeErrorException(null, "null email");
//		User u=new User();
//		u.setName(user.getName());
//		u.setEmail("123@gmail.com");
//		u.setPassword(user.getPassword());
//		u.setRole(user.getRole());
		User u = mapper.toEntity(dto);
		User saved= uRepo.save(u);
		return mapper.toDTO(saved);
		
	}
	public List<UserResponseDTO> getAllUsers(){
		List<User> users=uRepo.findAll();
		return mapper.toDTOList(users);
	}
	public UserResponseDTO getUserById(Long id) {
		User u =uRepo.findById(id).orElse(new User());
		
		return mapper.toDTO(u);
	}
	
	public UserResponseDTO getByEmail(String email) {
		User u = uRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("user not found"));
		return mapper.toDTO(u);
	}
	
	
}
