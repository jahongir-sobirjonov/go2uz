package uniqueproject.uz.go2uz.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uniqueproject.uz.go2uz.dto.auth.request.ChangePasswordRequest;
import uniqueproject.uz.go2uz.dto.auth.request.UserProfileRequest;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.repository.UserRepository;
import uniqueproject.uz.go2uz.service.UserProfileService;
import uniqueproject.uz.go2uz.service.UserService;


import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<?> getUserProfile(Principal principal) {

        return ResponseEntity.ok(userProfileService.getUserProfile(UUID.fromString(principal.getName())));
    }

    @PutMapping
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileRequest updatedUser, Principal principal) {
//        UserEntity user = userRepository.findByEmail(principal.getName()).orElse(null);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        user.setName(updatedUser.getName());
//        user.setSurname(updatedUser.getSurname());
//        user.setCity(updatedUser.getCity());
//        user.setEmail(updatedUser.getEmail());
//        user.setPhoneNumber(updatedUser.getPhoneNumber());
//        user.setProfilePhoto(updatedUser.getProfilePhoto());
//        user.setTelegramUsername(updatedUser.getTelegramUsername());
//        userRepository.save(user);
//
//        return ResponseEntity.ok("User updated successfully");
        return ResponseEntity.ok(userProfileService.updateUserProfile(UUID.fromString(principal.getName()), updatedUser));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Principal principal) {
//        UserEntity user = userRepository.findByEmail(principal.getName()).orElse(null);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
//        userRepository.save(user);
//
        return ResponseEntity.ok("Password changed successfully");
//        return ResponseEntity.ok(userProfileService.changePassword(changePasswordRequest, principal));
    }
}
