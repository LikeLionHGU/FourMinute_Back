package com.example.seazle.controller;

import com.example.seazle.dto.request.LoginRequest;
import com.example.seazle.dto.request.RegisterRequest;
import com.example.seazle.dto.response.MessageResponse;
import com.example.seazle.dto.response.UserNameResponse;
import com.example.seazle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) {
        Object nameResponse = userService.register(registerRequest);
        if(nameResponse==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        if(nameResponse.equals("duplicated")) return ResponseEntity.badRequest().body(new MessageResponse("duplicated"));
        return ResponseEntity.ok().body(nameResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(new UserNameResponse(SecurityContextHolder.getContext().getAuthentication().getName()));
    }


}
