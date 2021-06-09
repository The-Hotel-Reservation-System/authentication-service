package com.example.authenticationservice.service;

import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.CreateGuestRequest;
import com.example.authenticationservice.model.request.LoginRequest;
import com.example.authenticationservice.model.response.LoginResponse;

public interface AuthenticationService {

  LoginResponse login(LoginRequest loginRequest) throws Exception;

  GuestDto registerGuest(CreateGuestRequest createGuestRequest);
}
