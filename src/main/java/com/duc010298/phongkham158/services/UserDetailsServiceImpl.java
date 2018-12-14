package com.duc010298.phongkham158.services;

import com.duc010298.phongkham158.dao.AppRoleRepository;
import com.duc010298.phongkham158.dao.AppUserRepository;
import com.duc010298.phongkham158.entity.AppUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUserEntity appUserEntity = appUserRepository.findUserAccount(userName);

        List<String> roleNames = this.appRoleRepository.getRoleNames(appUserEntity.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new User(appUserEntity.getUserName(), appUserEntity.getEncrytedPassword(), grantList);

        return userDetails;
    }
}

