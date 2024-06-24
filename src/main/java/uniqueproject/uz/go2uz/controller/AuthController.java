package uniqueproject.uz.go2uz.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniqueproject.uz.go2uz.config.security.JwtResponse;
import uniqueproject.uz.go2uz.dto.auth.SignUp;
import uniqueproject.uz.go2uz.dto.response.AuthDto;
import uniqueproject.uz.go2uz.entity.UserType;
import uniqueproject.uz.go2uz.service.AuthService;
import uniqueproject.uz.go2uz.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    @PermitAll
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUp signUp, UserType userType){
        return ResponseEntity.ok().body(authService.addUser(signUp, userType));
    }

//    @GetMapping("/me")
//    public ResponseEntity<UserResponse> me(Principal principal){
//
//        return ResponseEntity.ok(userService.me(principal));
//    }

    @PermitAll
    @PostMapping("/sign-in")
    public JwtResponse signIn(@Valid @RequestBody AuthDto dto) {
        return authService.signIn(dto);
    }

}
