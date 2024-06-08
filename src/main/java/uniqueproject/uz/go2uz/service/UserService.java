package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.SignUp;
import uniqueproject.uz.go2uz.dto.auth.response.OrderResponse;
import uniqueproject.uz.go2uz.dto.auth.response.UserResponse;
import uniqueproject.uz.go2uz.entity.Order;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.entity.UserType;
import uniqueproject.uz.go2uz.entity.enums.OrderStatus;
import uniqueproject.uz.go2uz.entity.enums.UserRole;
import uniqueproject.uz.go2uz.exception.DataAlreadyExistsException;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.repository.UserRepository;

import java.security.Principal;
import java.util.UUID;

import static uniqueproject.uz.go2uz.entity.enums.UserRole.*;
import static uniqueproject.uz.go2uz.entity.enums.UserRole.Manager;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponse updateUser(UUID userId, UserRole role) {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new DataNotFoundException("User not found"));
            user.setRole(role);
            UserEntity updatedUserEntity = userRepository.save(user);
           return modelMapper.map(updatedUserEntity, UserResponse.class);
        }

    public  <T> T me(Principal principal) {
        UserEntity userEntity = userRepository.findById(UUID.fromString(principal.getName()))
                .orElseThrow(() -> new DataNotFoundException("User not found!"));;
        UserRole role = userEntity.getRole();
        if (role == ADMIN){
            UserResponse adminResponse = modelMapper.map(userEntity, UserResponse.class);
            adminResponse.setRole(ADMIN);
            return (T) adminResponse;
        } else if (role == USER) {
            UserResponse userResponse =  modelMapper.map(userEntity,UserResponse.class);
            userResponse.setRole(USER);
            return (T)userResponse;
        } else if (role == Manager) {
            UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
            userResponse.setRole(Manager);
            return (T) userResponse;
        } else if (role == SUPER_ADMIN) {
                UserResponse userResponse =  modelMapper.map(userEntity,UserResponse.class);
                userResponse.setRole(SUPER_ADMIN);
                return (T)userResponse;
            }

        return (T) modelMapper.map(userEntity,UserResponse.class);
    }
}
