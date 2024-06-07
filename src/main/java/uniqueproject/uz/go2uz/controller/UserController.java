package uniqueproject.uz.go2uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import uniqueproject.uz.go2uz.dto.auth.SignUp;
import uniqueproject.uz.go2uz.entity.UserType;
import uniqueproject.uz.go2uz.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUp signUp, UserType userType){
        return ResponseEntity.ok().body(userService.addUser(signUp, userType));
    }


}
