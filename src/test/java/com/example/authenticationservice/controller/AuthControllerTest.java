package com.example.authenticationservice.controller;


import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.CreateGuestRequest;
import com.example.authenticationservice.model.request.LoginRequest;
import com.example.authenticationservice.model.response.LoginResponse;
import com.example.authenticationservice.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

public class AuthControllerTest {
  private final String EMAIL = "test@gmail.com";
  private final String NAME = "Name";
  private final String PHONE = "1234567890";
  private String PASSWORD = "PASSWORD";
  private final BigInteger ID = BigInteger.ONE;
  public AuthenticationController authenticationController;
  public AuthenticationService authenticationService;
  GuestDto guestDto;

  private GuestDto buildGuestDto() {
    return GuestDto.builder()
        .id(ID)
        .phone(PHONE)
        .name(NAME)
        .email(EMAIL)
        .build();
  }

  @BeforeEach
  public void init() {
    authenticationService = Mockito.mock(AuthenticationService.class);
    authenticationController = new AuthenticationControllerImpl(authenticationService);
    guestDto = buildGuestDto();
  }

  @Test
  public void login() throws Exception {
    LoginRequest request = buildLoginRequest();
    Mockito.when(authenticationService.login(Mockito.any(LoginRequest.class)))
        .thenReturn(LoginResponse.builder().build());
    ResponseEntity<LoginResponse> responses = authenticationController.login(request);
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void register() throws Exception {
    CreateGuestRequest request = buildCreateGuestRequest();
    Mockito.when(authenticationService.login(Mockito.any(LoginRequest.class)))
        .thenReturn(LoginResponse.builder().build());
    ResponseEntity<GuestDto> responses = authenticationController.register(request);
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  private CreateGuestRequest buildCreateGuestRequest() {
    return CreateGuestRequest.builder()
        .email(EMAIL)
        .name(NAME)
        .phone(PHONE)
        .password(PASSWORD)
        .build();
  }

  private LoginRequest buildLoginRequest() {
    return LoginRequest.builder()
        .email(EMAIL)
        .password(PASSWORD)
        .build();
  }
}
