package com.foyhWebAppApi.Account.controller;

import com.foyhWebAppApi.Account.jwt.JwtTokenProvider;
import com.foyhWebAppApi.Account.payload.LoginRequest;
import com.foyhWebAppApi.Account.payload.LoginResponse;
import com.foyhWebAppApi.Account.payload.RandomStuff;
import com.foyhWebAppApi.Account.user.CustomUserDetails;
import com.foyhWebAppApi.Account.user.User;
import com.foyhWebAppApi.Account.user.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LodaRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Xác thực từ username và password.
        System.out.println("mhvm"+loginRequest.getUsername());
        Authentication authentication;
        try{
         authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
            );

        }catch (Exception e){
            return new LoginResponse("loss");
        }
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);

    }
    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.save(user));
    }
    @GetMapping("/getMessage")
    public String getms(
            @RequestParam(name="hub.mode") String mode,
            @RequestParam(name="hub.verify_token") String verifytoken
            ,@RequestParam(name="hub.challenge") String challenge
    ){  System.out.println(challenge+"/"+mode+"/"+verifytoken);
        return challenge;

    }

    @PostMapping("/getMessage")
    public String getmsR(
            @RequestBody final String payload,
            @RequestHeader("X-Hub-Signature") final String signature
    ){  System.out.println(payload+"/"+signature);
    return "";
    }

}