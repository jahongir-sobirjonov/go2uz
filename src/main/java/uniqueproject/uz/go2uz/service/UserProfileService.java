package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.dto.auth.request.UserProfileRequest;
import uniqueproject.uz.go2uz.dto.auth.response.UserProfileResponse;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.exception.DataNotFoundException;
import uniqueproject.uz.go2uz.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileResponse getUserProfile(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .city(user.getCity())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .telegramUsername(user.getTelegramUsername())
                .profilePhoto(user.getProfilePhoto())
                .build();
    }

    public UserProfileResponse updateUserProfile(UUID userId, UserProfileRequest profileRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // Check if the current password matches the user's password
        if (!passwordEncoder.matches(profileRequest.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid current password");
        }

        // Update user profile information
        user.setName(profileRequest.getName());
        user.setSurname(profileRequest.getSurname());
        user.setCity(profileRequest.getCity());
        user.setEmail(profileRequest.getEmail());
        user.setPhoneNumber(profileRequest.getPhoneNumber());
        user.setTelegramUsername(profileRequest.getTelegramUsername());
        user.setProfilePhoto(profileRequest.getProfilePhoto());

        // If a new password is provided, update the password
        if (profileRequest.getNewPassword() != null && !profileRequest.getNewPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(profileRequest.getNewPassword()));
        }

        // Save the updated user entity
        UserEntity updatedUser = userRepository.save(user);

        // Return the updated user profile response
        return UserProfileResponse.builder()
                .id(updatedUser.getId())
                .name(updatedUser.getName())
                .surname(updatedUser.getSurname())
                .city(updatedUser.getCity())
                .email(updatedUser.getEmail())
                .phoneNumber(updatedUser.getPhoneNumber())
                .telegramUsername(updatedUser.getTelegramUsername())
                .profilePhoto(updatedUser.getProfilePhoto())
                .build();
    }

}
