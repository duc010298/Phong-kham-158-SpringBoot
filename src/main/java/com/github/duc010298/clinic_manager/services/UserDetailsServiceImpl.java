package com.github.duc010298.clinic_manager.services;

import com.github.duc010298.clinic_manager.entity.AppUserEntity;
import com.github.duc010298.clinic_manager.repository.AppRoleRepository;
import com.github.duc010298.clinic_manager.repository.AppUserRepository;
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
        AppUserEntity appUserEntity = appUserRepository.findByUserName(userName);

        List<String> roleNames = this.appRoleRepository.getRoleNames(appUserEntity.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        return new User(appUserEntity.getUserName(), appUserEntity.getEncryptedPassword(), grantList);
    }
}

