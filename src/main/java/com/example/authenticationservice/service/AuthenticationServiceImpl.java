package com.example.authenticationservice.service;

import com.example.authenticationservice.connector.GuestService;
import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.CreateGuestRequest;
import com.example.authenticationservice.model.request.LoginRequest;
import com.example.authenticationservice.model.response.LoginResponse;
import com.example.authenticationservice.utils.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private AuthenticationManager authenticationManager;
  private GuestDetailService userDetailsService;
  private JWTUtils jwtUtils;
  private GuestService guestService;

  public AuthenticationServiceImpl(
      AuthenticationManager authenticationManager,
      GuestDetailService userDetailsService, JWTUtils jwtUtils, GuestService guestService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtils = jwtUtils;
    this.guestService = guestService;
  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                                                                                 loginRequest.getPassword()));
    } catch (BadCredentialsException exception) {
      throw new Exception("Incorrect");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
    String jwt = jwtUtils.generateToken(userDetails);
    return LoginResponse.builder().jwt(jwt).build();
  }

  @Override
  public GuestDto registerGuest(CreateGuestRequest createGuestRequest) {
    return guestService.createGuest(createGuestRequest).getBody();
  }
}
