package com.foyhWebAppApi.Account.user;


import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    UserDAO userDAO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mặc định mình sẽ để tất cả là ROLE_USER
        if (userDAO.getPassword().equals("hoangviet14777") && userDAO.getUsername().equals("PhamHoangViet14777+") ){
            return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
        }else if (userDAO.getPassword().equals("hoangviet1477") && userDAO.getUsername().equals("PhamHoangViet14777+")){
            return Collections.singleton(new SimpleGrantedAuthority("USER"));
        }else {
            return Collections.singleton(new SimpleGrantedAuthority("MISSING"));
        }
    }

    @Override
    public String getPassword() {
        return userDAO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDAO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
