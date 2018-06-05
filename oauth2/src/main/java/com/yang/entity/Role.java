package com.yang.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Role {
    protected Long id;
    protected String name;
    protected String value;

//    private Set<Authority> authorities=new HashSet<>();
//
//    public Set<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<Authority> authorities) {
//        this.authorities = authorities;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
