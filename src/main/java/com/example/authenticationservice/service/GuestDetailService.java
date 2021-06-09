package com.example.authenticationservice.service;

import com.example.authenticationservice.connector.GuestService;
import com.example.authenticationservice.model.dto.GuestDto;
import com.example.authenticationservice.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GuestDetailService implements UserDetailsService {

  @Autowired
  GuestService guestService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return new User("hophanminh", "hophanminh", new ArrayList<>());
  }

}
