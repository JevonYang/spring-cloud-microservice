package com.yang.entity;

import java.util.HashSet;
import java.util.Set;

public class RoleWithAuthorites extends Role {
    protected Set<Authority> authorities=new HashSet<>();

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
