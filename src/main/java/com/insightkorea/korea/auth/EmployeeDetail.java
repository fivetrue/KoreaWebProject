package com.insightkorea.korea.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.insightkorea.korea.Global;
import com.insightkorea.korea.model.Employee;

public class EmployeeDetail implements UserDetails {

    private static final long serialVersionUID = 1L;
    
    private static final String ROLE_PREFIX = "ROLE_";
    
    private Employee employee;

    public EmployeeDetail(Employee emp) {
		// TODO Auto-generated constructor stub
    	this.employee = emp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(employee.getAdmin() == 1){
			return AuthorityUtils.createAuthorityList(ROLE_PREFIX + Global.Roles.ADMIN
					, ROLE_PREFIX + Global.Roles.EMPLOYEE);
		}else{
			return AuthorityUtils.createAuthorityList(ROLE_PREFIX + Global.Roles.EMPLOYEE);
		}
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return employee.getEmployeeId();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return employee.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getUsername() + "/" + getPassword() + "/" + getAuthorities();
	}
}
