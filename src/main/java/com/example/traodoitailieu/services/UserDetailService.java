/*
package com.example.traodoitailieu.services;

import com.example.traodoitailieu.entities.Role;
import com.example.traodoitailieu.entities.User;
import com.example.traodoitailieu.repositories.RoleRepo;
import com.example.traodoitailieu.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getUsername(email);
        if(user==null){
            throw new UsernameNotFoundException("Email not found");
        }

        */
/*List<String> roleNames = new ArrayList<>();

        List<Role> c = (List<Role>) roleRepo.findAll();

        List<Integer> a = userRepo.getRoleNames(user.getUser_id());
        for (int m: a) {
            for (Role k: c) {
                if(m == k.getRole_id()){
                    roleNames.add(k.getRole_name());
                    break;
                }
            }
        }

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUser_name(), //
                user.getPassword(), grantList);

        return userDetails;*//*


        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for(Role role: roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole_name()));
        }

        return new org.springframework.security.core.
                userdetails.User(user.getEmail(),user.getPassword(),grantedAuthorities);
    }
}
*/
