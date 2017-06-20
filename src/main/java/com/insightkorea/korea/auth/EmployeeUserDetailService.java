package com.insightkorea.korea.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.insightkorea.korea.model.Employee;
import com.insightkorea.korea.repository.EmployeeRepository;

@Repository
public class EmployeeUserDetailService implements UserDetailsService{
	
	private EmployeeRepository mEmployeeRepository;
	
	@Autowired
	public EmployeeUserDetailService(EmployeeRepository employeeRepository) {
		// TODO Auto-generated constructor stub
		mEmployeeRepository = employeeRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee emp = mEmployeeRepository.findByName(username);
		if (emp == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		return new EmployeeDetail(emp);
	}
}
