package com.foyhWebAppApi.Account.user;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        UserDAO userDAO = userRepository.findByUsername(username);
        if (userDAO == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(userDAO);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(Long id) {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return new CustomUserDetails(userDAO);
    }
    public UserDAO getUserById(Long id){
    UserDAO userDAO = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
     );
        return userDAO;
    }

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserDAO save(User user) {
        UserDAO newUser = new UserDAO();
        UserDAO testUser = userRepository.findByUsername(user.getUsername());
        if (testUser!=null){
            System.out.println(testUser.toString());
        }
        if (testUser==null){
            newUser.setUsername(user.getUsername());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setFullname(user.getFullname());
            newUser.setBirthday(user.getBirthday());
            userRepository.save(newUser);


        }else {
            newUser.setUsername("false");
            newUser.setPassword(passwordEncoder.encode("fales"));
        }

        return  newUser;
    }

    public JSONArray getAll() throws SQLException {
        JSONArray arr = new connect_mysql("user","root","").getALLDataUser();
           return arr;
    }
    public JSONObject getDataUser(Long id)throws SQLException {
        JSONObject obj = new connect_mysql("user","root","").getDataUser(id+"");
        return obj;
    }

    public boolean upDateData(Long id,JSONArray dtd,JSONArray bhrt,JSONArray bhdt) throws SQLException {
        new connect_mysql("user","root","").UpdateDataUser(dtd,bhrt,bhdt,id+"");
        return true;
    }

}