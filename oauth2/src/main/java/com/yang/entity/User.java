package com.yang.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

    protected Long id;
    protected String username;
    protected String password;
    protected String company;
    protected String department;
    protected String createBy;
    protected Date createDate;

//    private Set<GrantedAuthority> authorities= new HashSet<>();
//
//    private Set<Role> roles = new HashSet<>();
//
//    public Set<GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> userAuthorities= new HashSet<>();
//        for (Role role:this.roles) {
//            userAuthorities.add(new SimpleGrantedAuthority(role.getValue()));
//            for (Authority authority:role.getAuthorities()) {
//                userAuthorities.add(new SimpleGrantedAuthority(authority.getValue()));
//            }
//        }
//        return authorities;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
