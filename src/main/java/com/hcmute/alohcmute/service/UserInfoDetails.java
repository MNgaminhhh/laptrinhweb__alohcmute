package com.hcmute.alohcmute.service;
import com.hcmute.alohcmute.entity.User;

import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.userdetails.UserDetails; 

import java.util.Arrays; 
import java.util.Collection; 
import java.util.List; 
import java.util.stream.Collectors; 

public class UserInfoDetails implements UserDetails { 
	private Long userId;
	private String name; 
	private String password; 
	private List<GrantedAuthority> authorities; 

	public UserInfoDetails(User userInfo) { 
		userId = userInfo.getUserId();
		name = userInfo.getUsername(); 
		password = userInfo.getPassword(); 
		authorities = Arrays.stream(userInfo.getIsAdmin().split(",")) 
				.map(SimpleGrantedAuthority::new) 
				.collect(Collectors.toList()); 
	} 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return authorities;  
	} 
	
	public Long getUserId() {
        return userId;
    }
	@Override
	public String getPassword() { 
		return password; 
	} 

	@Override
	public String getUsername() { 
		return name; 
	} 

	@Override
	public boolean isAccountNonExpired() { 
		return true; 
	} 

	@Override
	public boolean isAccountNonLocked() { 
		return true; 
	} 

	@Override
	public boolean isCredentialsNonExpired() { 
		return true; 
	} 

	@Override
	public boolean isEnabled() { 
		return true; 
	} 
} 
