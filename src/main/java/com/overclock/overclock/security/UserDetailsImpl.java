package com.overclock.overclock.security;

import com.overclock.overclock.model.User;
import com.overclock.overclock.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {
    private BigInteger id;
    private String email;
    private Role role;
    private Date registrationDate;
    private boolean isActive;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public static UserDetails fromUser(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        UserDetailsImpl userDetails = new UserDetailsImpl(user.getUserName(), user.getPassword(),
                Collections.singletonList(authority));

        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setRole(user.getRole());
        userDetails.setActive(user.isActive());
        userDetails.setRegistrationDate(user.getRegistrationDate());
        return userDetails;
    }

    public User toUser() {
        return new User.Builder()
                .setId(this.getId())
                .setUserName(this.getUsername())
                .setEmail(this.getEmail())
                .setRole(this.getRole())
                .setRegistrationDate(this.getRegistrationDate())
                .setIsActive(this.isActive())
                .build();
    }
}
