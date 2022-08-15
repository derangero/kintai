package com.example.demo.serviceImple;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//つけたらエラー！なんでも付ければ良いわけではない
//@Service
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
    
	private String username;
    private String password;

	private Collection<GrantedAuthority> authorities;
    
	private String employeeName;

    public UserDetailsImpl(String username, String password, Collection<GrantedAuthority> authorities,
    		String employeeName) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.employeeName = employeeName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
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
	public String getEmployeeName() {
		return employeeName;
	}
}
