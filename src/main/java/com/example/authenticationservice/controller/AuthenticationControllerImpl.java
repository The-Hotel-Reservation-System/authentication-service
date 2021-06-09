package com.example.authenticationservice.controller;

import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.CreateGuestRequest;
import com.example.authenticationservice.model.request.LoginRequest;
import com.example.authenticationservice.model.response.LoginResponse;
import com.example.authenticationservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationControllerImpl implements AuthenticationController{

  private AuthenticationService authenticationService;

  public AuthenticationControllerImpl(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Override
  public ResponseEntity<LoginResponse> login(@RequestBody
                                                 LoginRequest loginRequest) throws Exception {
    return ResponseEntity.ok(authenticationService.login(loginRequest));
  }

  @Override
  public ResponseEntity<GuestDto> register(CreateGuestRequest createGuestRequest) throws Exception {
    return ResponseEntity.ok(authenticationService.registerGuest(createGuestRequest));
  }
}
