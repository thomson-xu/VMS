package com.tms.author.service;

import com.tms.author.bean.SysUser;
import com.tms.author.bean.SysUsersRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private SysUserService userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        SysUser user = userService.findBySso(ssoId);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(SysUser user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(SysUsersRoles sysUsersRoles : user.getSysUsersRoleses()){
            System.out.println("UserProfile : "+sysUsersRoles);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+sysUsersRoles.getRoleId()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }

}