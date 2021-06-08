package com.example.authenticationservice.controller;


import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.CreateGuestRequest;
import com.example.authenticationservice.model.request.LoginRequest;
import com.example.authenticationservice.model.response.LoginResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthenticationController {
  @PostMapping("/login")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login success.",
          content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to create guest.", content = @Content)
  })
  ResponseEntity<LoginResponse> login(@RequestBody
                                          LoginRequest loginRequest) throws Exception;

  @PostMapping("/register")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Register success.",
          content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateGuestRequest.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to register.", content = @Content)
  })
  ResponseEntity<GuestDto> register(@RequestBody
                                        CreateGuestRequest createGuestRequest) throws Exception;
}
