package com.example.authenticationservice.connector;

import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.CreateGuestRequest;
import com.example.authenticationservice.model.request.LoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "${guest-service.name}", path = "${guest-service.path.host}")
public interface GuestService {
  @PostMapping
  ResponseEntity<GuestDto> createGuest(@Valid @RequestBody CreateGuestRequest request);
}
