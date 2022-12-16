package com.hamitmizrak.api.impl;

import com.hamitmizrak.business.dto.UserDto;
import com.hamitmizrak.business.services.IAuthenticationService;
import com.hamitmizrak.business.services.IUserServices;
import com.hamitmizrak.security.jwt.JwtProviderImpl;
import com.hamitmizrak.api.IAuthenticationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//lombok
//@RequiredArgsConstructor

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationApiImpl implements IAuthenticationApi {

    //Injection
    private  IAuthenticationService authenticationService;
    private  IUserServices userServices;
    private   AuthenticationManager authenticationManager;
    private  JwtProviderImpl jwtUtils;

    @Autowired
    public AuthenticationApiImpl(IAuthenticationService authenticationService, IUserServices userServices, AuthenticationManager authenticationManager, JwtProviderImpl jwtUtils) {
        this.authenticationService = authenticationService;
        this.userServices = userServices;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    //REGISTER
    // http://localhost:1111/api/authentication/register
    @Override
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        // kullanıcı adımız unique olmalıdır.
        if (userServices.findUsername(userDto.getUsername()).isPresent()) {
            //aynı kullanıcı varsa conflict oluşturalım
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userServices.createUser(userDto), HttpStatus.CREATED);
    }

    //LOGIN
    // http://localhost:1111/api/authentication/login
    @Override
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        //UserPrincipal userLoginDetails= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(userLoginDetails);
        return new ResponseEntity<>(authenticationService.loginReturnJwt(userDto), HttpStatus.OK);
    }
}
