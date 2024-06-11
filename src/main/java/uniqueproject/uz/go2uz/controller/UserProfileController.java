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

    @GetMapping("/me")
    public ResponseEntity<?> getUserProfile(Principal principal) {

        return ResponseEntity.ok(userProfileService.getUserProfile(UUID.fromString(principal.getName())));
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileRequest updatedUser, Principal principal) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(UUID.fromString(principal.getName()), updatedUser));
    }
}
