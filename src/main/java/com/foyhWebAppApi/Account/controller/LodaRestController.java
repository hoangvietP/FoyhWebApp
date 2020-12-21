package com.foyhWebAppApi.Account.controller;

import com.foyhWebAppApi.Account.jwt.JwtTokenProvider;
import com.foyhWebAppApi.Account.payload.LoginRequest;
import com.foyhWebAppApi.Account.payload.LoginResponse;
import com.foyhWebAppApi.Account.payload.RandomStuff;
import com.foyhWebAppApi.Account.user.CustomUserDetails;
import com.foyhWebAppApi.Account.user.User;
import com.foyhWebAppApi.Account.user.UserDAO;
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
import java.sql.SQLException;
import java.util.ArrayList;

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

    @PostMapping("/getAllData")
    public JSONObject getAllData(@Valid @RequestHeader("Authorization") String Autho) {
        // Xác thực từ username và password.
        JSONObject datart = new JSONObject();
        Boolean da = tokenProvider.validateToken(Autho);
        if (da==true){
            Long userId = tokenProvider.getUserIdFromJWT(Autho);
            if (userId==1 || userId==2 || userId==3){
                JSONArray data = null;
                try {
                    data = new UserService().getAll();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                datart.put("data",data);
            }
        }

        return datart;
    }

    @PostMapping("/getDataUser")
    public JSONObject getDataUser(@Valid @RequestHeader("Authorization") String Autho) throws SQLException {
        JSONObject datart = new JSONObject();
        String jw=Autho;
        String[] jwt1= Autho.split(" ");
        if (jwt1.length>1){
            String[] jwt= Autho.split(" ");
            jw=jwt[1];
        }

        char[] a = jw.toCharArray();
        String jwtt="";
        for (int i=0; i<=a.length-1;i++){
            if (a[i]!='"'){
                jwtt+=a[i];
            }
        }
        Boolean da = tokenProvider.validateToken(jwtt);
        if (da==true){
            Long userId = tokenProvider.getUserIdFromJWT(jwtt);
            return new UserService().getDataUser(userId);
        }else {
            datart.put("dataUser","false");
        }
        return datart;
    }


    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.save(user));
    }


}