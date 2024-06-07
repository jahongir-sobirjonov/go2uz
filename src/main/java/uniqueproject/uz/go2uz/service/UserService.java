package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.SignUp;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.entity.UserType;
import uniqueproject.uz.go2uz.entity.enums.UserRole;
import uniqueproject.uz.go2uz.exception.DataAlreadyExistsException;
import uniqueproject.uz.go2uz.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public String addUser(SignUp signUp, UserType userType) {
        if (userRepository.existsByEmail(signUp.getEmail())){
            throw new DataAlreadyExistsException("This email already exists: " + signUp.getEmail());
        }
        UserEntity user = modelMapper.map(signUp, UserEntity.class);
        user.setUserType(userType);
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return "User successfully registered";
    }
}
