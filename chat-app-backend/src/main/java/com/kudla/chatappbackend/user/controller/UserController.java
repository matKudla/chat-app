package com.kudla.chatappbackend.user.controller;

import com.kudla.chatappbackend.user.model.Dto.AppUserDto;
import com.kudla.chatappbackend.user.model.Dto.AuthenticationUserRequest;
import com.kudla.chatappbackend.user.model.Entity.AppUser;
import com.kudla.chatappbackend.user.service.AppUserDetailsService;
import com.kudla.chatappbackend.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService appUserDetailsService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    @PostMapping("/authenticate")
    public ResponseEntity<AppUserDto> createAuthenticationToken(@RequestBody AuthenticationUserRequest request) throws Exception {
     try{
         authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
         );
     } catch (BadCredentialsException e){
         throw new Exception("Incorrect username or password", e);
     }

     final AppUser appUser = appUserDetailsService.loadUserByUsername(request.getUsername());
     final String jwt = jwtUtil.generateToken(appUser);
     AppUserDto dto = modelMapper.map(appUser, AppUserDto.class);
     dto.setToken(jwt);
     return ResponseEntity.ok(dto);

    }



}
