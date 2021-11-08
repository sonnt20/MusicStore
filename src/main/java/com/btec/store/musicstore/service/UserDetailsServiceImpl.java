package com.btec.store.musicstore.service;

import com.btec.store.musicstore.model.entity.GroupRoleEntity;
import com.btec.store.musicstore.model.entity.KhUserEntity;
import com.btec.store.musicstore.repository.KhUserRepository;
import com.btec.store.musicstore.repository.RoleRepository;
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
    @Autowired
    private KhUserRepository khUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        tìm user từ trong database
        KhUserEntity user = khUserRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
//        Lấy quyền trong database theo user
        GroupRoleEntity groupRole = roleRepository.getGroupRoleEntitiesByUser(user.getUsername());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        if (groupRole != null) {
            for (String role : groupRole.getRoles().split(",")) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new User(user.getUsername(),
                user.getPassword(), grantList);

        return userDetails;
    }
}
