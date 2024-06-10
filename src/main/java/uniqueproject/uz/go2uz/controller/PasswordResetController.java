package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import uniqueproject.uz.go2uz.dto.auth.request.PasswordResetRequest;
import uniqueproject.uz.go2uz.dto.auth.response.PasswordResetDto;
import uniqueproject.uz.go2uz.service.PasswordResetService;

@RestController
@RequestMapping("/api/password-reset")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordReset(@RequestBody PasswordResetRequest request) {
        passwordResetService.sendPasswordResetLink(request.getEmail());
        return ResponseEntity.ok("Password reset link sent.");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto passwordResetDto) {
        passwordResetService.resetPassword(passwordResetDto.getToken(), passwordResetDto.getNewPassword());
        return ResponseEntity.ok("Password has been reset.");
    }


}
