package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.config.security.JwtResponse;
import uniqueproject.uz.go2uz.config.security.JwtService;
import uniqueproject.uz.go2uz.dto.auth.SignUp;
import uniqueproject.uz.go2uz.dto.response.AuthDto;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.entity.UserType;
import uniqueproject.uz.go2uz.entity.enums.UserRole;
import uniqueproject.uz.go2uz.exception.DataAlreadyExistsException;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
//    private JwtTokenProvider tokenProvider;


    public String addUser(SignUp signUp, UserType userType) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            throw new DataAlreadyExistsException("This email already exists: " + signUp.getEmail());
        }

        // Map SignUp to UserEntity
        UserEntity user = modelMapper.map(signUp, UserEntity.class);

        // Hash the password
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));

        // Set user type and role
        user.setUserType(userType);
        user.setRole(UserRole.USER);

        // Save the user to the repository
        userRepository.save(user);

        return "User successfully registered";
    }


    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return new JwtResponse(jwtService.generateToken(user));
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
        }
    }
}
