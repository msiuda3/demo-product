package com.example.demo_oproduct.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/register")
    public ResponseEntity<AuthorizationResponse> register(
            @RequestBody AuthorizationRequest request
    ){
        return ResponseEntity.ok(authorizationService.register(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponse> login(
            @RequestBody AuthorizationRequest request
    ){
        return ResponseEntity.ok(authorizationService.login(request));
    }
}
