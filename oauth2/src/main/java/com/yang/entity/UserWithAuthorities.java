package com.yang.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class UserWithAuthorities extends User {

    private Set<GrantedAuthority> authorities= new HashSet<>();

    private Set<RoleWithAuthorites> roles = new HashSet<>();

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> userAuthorities= new HashSet<>();
        for (RoleWithAuthorites role:this.roles) {
            userAuthorities.add(new SimpleGrantedAuthority(role.getValue()));
            for (Authority authority:role.getAuthorities()) {
                userAuthorities.add(new SimpleGrantedAuthority(authority.getValue()));
            }
        }
        return authorities;
    }

}
