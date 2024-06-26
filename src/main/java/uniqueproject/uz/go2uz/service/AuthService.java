package uniqueproject.uz.go2uz.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public String addUser(SignUp signUp, UserType userType) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            throw new DataAlreadyExistsException("This email already exists: " + signUp.getEmail());
        }

        UserEntity user = modelMapper.map(signUp, UserEntity.class);
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));
        user.setUserType(userType);
        user.setRole(UserRole.USER);
        userRepository.save(user);

        return "User successfully registered";
    }

    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
        }
    }

    public JwtResponse refreshToken(String refreshToken) {
        Jws<Claims> claimsJws = jwtService.extractToken(refreshToken);
        String userId = claimsJws.getBody().getSubject();
        UserEntity user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return new JwtResponse(newAccessToken, newRefreshToken);
    }
}
