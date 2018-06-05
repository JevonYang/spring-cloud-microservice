package com.yang.service;

import com.yang.dao.AuthorityDao;
import com.yang.dao.RoleDao;
import com.yang.dao.UserDao;
import com.yang.entity.Authority;
import com.yang.entity.Role;
import com.yang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@Service
public class CostomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User tempUser = userDao.findOneByUsername(username);
        List<Role> roles = roleDao.selectRolesByUsername(username);
        List<Authority> authorities= authorityDao.findAuthoritiesByRole(roles);

        try{
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role: roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getValue()));
            }
            for (Authority authority:authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getValue()));
            }
            return new org.springframework.security.core.userdetails.User(tempUser.getUsername(), tempUser.getPassword(), grantedAuthorities);
        }catch (Exception e){
            throw  new UsernameNotFoundException("禁止！用户"+username+"不存在，请重新再尝试");
        }

//        return tempUser.map(user -> {
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//            for (Role role: roles) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(role.getValue()));
//            }
//            for (Authority authority:authorities) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getValue()));
//            }
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//        }).orElseThrow(()->new UsernameNotFoundException("禁止！用户"+username+"不存在，请重新再尝试"));
    }
}
