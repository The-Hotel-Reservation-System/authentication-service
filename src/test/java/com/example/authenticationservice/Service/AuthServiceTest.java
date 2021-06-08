package com.example.authenticationservice.Service;

import com.example.authenticationservice.connector.GuestService;
import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.CreateGuestRequest;
import com.example.authenticationservice.model.request.LoginRequest;
import com.example.authenticationservice.model.response.LoginResponse;
import com.example.authenticationservice.service.AuthenticationService;
import com.example.authenticationservice.service.AuthenticationServiceImpl;
import com.example.authenticationservice.service.GuestDetailService;
import com.example.authenticationservice.utils.JWTUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
public class AuthServiceTest {

  private final String EMAIL = "EMAIL";
  private String PASSWORD = "PASSWORD";
  private String JWT = "JWT";
  private LoginRequest loginRequest;
  private LoginResponse loginResponse;
  private final String NAME = "Name";
  private final String PHONE = "1234567890";
  private final BigInteger ID = BigInteger.ONE;

  private AuthenticationService authenticationService;
  @Mock
  private GuestService guestService;
  @Mock
  private AuthenticationManager authenticationManager;
  @Mock
  private GuestDetailService userDetailsService;
  @Mock
  private JWTUtils jwtUtils;

  public static class MockSecurityContext implements SecurityContext {

    private Authentication authentication;

    public MockSecurityContext(Authentication authentication) {
      this.authentication = authentication;
    }

    @Override
    public Authentication getAuthentication() {
      return this.authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
      this.authentication = authentication;
    }
  }

  @BeforeEach
  void init() {
    authenticationService = new AuthenticationServiceImpl(authenticationManager,
                                                          userDetailsService,
                                                          jwtUtils,
                                                          guestService);
    loginRequest = buildLoginRequest();
    loginResponse = buildLoginResponse();
  }

  @Test
  void loginSuccess_shouldWork() throws Exception {
    Mockito.when(jwtUtils.generateToken(Mockito.any(UserDetails.class))).thenReturn(JWT);
    Mockito.when(userDetailsService.loadUserByUsername(Mockito.any(String.class)))
        .thenReturn(buildUserDetails());
    LoginResponse response = authenticationService.login(loginRequest);
    Assertions.assertEquals(loginResponse, response);
  }

  @Test
  void loginFail_shouldThrowException() throws Exception {
    Mockito.when(authenticationManager.authenticate(Mockito.any(Authentication.class)))
        .thenThrow(BadCredentialsException.class);
    Assertions.assertThrows(Exception.class,() -> authenticationService.login(loginRequest));
  }

  @Test
  void createGuess_shouldWork() throws Exception {
    CreateGuestRequest request = buildCreateGuestRequest();
    GuestDto guestDto = buildGuestDto();
    Mockito.when(guestService.createGuest(Mockito.any(CreateGuestRequest.class)))
        .thenReturn(ResponseEntity.ok(guestDto));
    GuestDto response = authenticationService.registerGuest(request);
    Assertions.assertEquals(guestDto.getEmail(), response.getEmail());
    Assertions.assertEquals(guestDto.getId(), response.getId());
    Assertions.assertEquals(guestDto.getName(), response.getName());
    Assertions.assertEquals(guestDto.getPhone(), response.getPhone());

  }

  private CreateGuestRequest buildCreateGuestRequest() {
    return CreateGuestRequest.builder()
        .email(EMAIL)
        .name(NAME)
        .phone(PHONE)
        .password(PASSWORD)
        .build();
  }

  private GuestDto buildGuestDto() {
    return GuestDto.builder()
        .id(ID)
        .phone(PHONE)
        .name(NAME)
        .email(EMAIL)
        .build();
  }

  private UserDetails buildUserDetails() {
    return new User(EMAIL, PASSWORD, new ArrayList<>());

  }

  private LoginRequest buildLoginRequest() {
    return LoginRequest.builder()
        .email(EMAIL)
        .password(PASSWORD)
        .build();
  }

  private LoginResponse buildLoginResponse() {
    return LoginResponse.builder()
        .jwt(JWT)
        .build();
  }

}
