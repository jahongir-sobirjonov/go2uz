package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.dto.auth.SignUp;
import uniqueproject.uz.go2uz.dto.auth.response.UserResponse;
import uniqueproject.uz.go2uz.entity.UserEntity;
import uniqueproject.uz.go2uz.entity.UserType;
import uniqueproject.uz.go2uz.entity.enums.UserRole;
import uniqueproject.uz.go2uz.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/update-user-role/{userId}/role")
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable UUID userId,
            @RequestParam UserRole role) {
        UserResponse userResponse = userService.updateUserRole(userId, role);
        return ResponseEntity.ok(userResponse);
    }





}
