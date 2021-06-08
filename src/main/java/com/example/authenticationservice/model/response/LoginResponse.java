package com.example.authenticationservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

  String jwt;
}
