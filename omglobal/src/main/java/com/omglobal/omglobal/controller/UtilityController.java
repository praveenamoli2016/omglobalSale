package com.omglobal.omglobal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omglobal.omglobal.config.TokenProvider;
import com.omglobal.omglobal.model.request.RoleRequest;
import com.omglobal.omglobal.model.request.UserLoginRequest;
import com.omglobal.omglobal.model.request.UserRequest;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UtilityService utilityService;


    @PostMapping("/createRole")
    public Response createRole(@Valid @RequestBody RoleRequest roleRequest){
        return this.utilityService.createRole(roleRequest);
    }


    @PostMapping("/resister")
    public Response createUser(@Valid @RequestBody UserRequest userRequest) {
        return this.utilityService.createUser(userRequest);
    }


    @DeleteMapping("/delete/{id}")
    public Response deleteUser(@PathVariable Long id){
        return utilityService.deleteUser(id);
    }


    @PostMapping("/login")
    public Response userLoginGenerateToken(@Valid @RequestBody UserLoginRequest userLoginRequest) throws AuthenticationException  {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUserName(),
                        userLoginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Response response=new Response("Success","200","user login and token generate successfully",token);
        return response;
       //return this.utilityService.userLogin(userLoginRequest);
    }
}
