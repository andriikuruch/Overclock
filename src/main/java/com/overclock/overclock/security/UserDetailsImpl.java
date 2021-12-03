package com.overclock.overclock.security;

import com.overclock.overclock.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl extends User {
    private BigInteger id;
    private String email;
    private Role role;

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static UserDetails fromUser(com.overclock.overclock.model.User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        UserDetailsImpl userDetails = new UserDetailsImpl(user.getUserName(), user.getPassword(),
                Collections.singletonList(authority));

        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setRole(user.getRole());
        return userDetails;
    }
}
