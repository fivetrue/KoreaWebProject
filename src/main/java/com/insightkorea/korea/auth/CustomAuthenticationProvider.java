package com.insightkorea.korea.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	private EmployeeUserDetailService mEmployeeUserDetailService;
	
	@Autowired
	public CustomAuthenticationProvider(EmployeeUserDetailService userDetailService) {
		// TODO Auto-generated constructor stub
		mEmployeeUserDetailService = userDetailService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException, AuthenticationException {
		// TODO Auto-generated method stub
		String empName = (String)authentication.getPrincipal();		
		String empId = (String)authentication.getCredentials();
		
		logger.info("Try to find user " + empName);
		UserDetails details = mEmployeeUserDetailService.loadUserByUsername(empName);
		
		logger.info("User credentials validation " + empName);
		// check whether user's credentials are valid.
		if(details == null || !details.getPassword().equals(empId)){
			throw new BadCredentialsException("Bad credentials");
		}
		
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(details.getUsername()
        		, details.getPassword(), details.getAuthorities());
        result.setDetails(details);
        logger.info("Wellcome to visit " + result.getName() + ", " + result.getAuthorities());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
