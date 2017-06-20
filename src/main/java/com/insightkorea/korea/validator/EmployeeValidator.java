package com.insightkorea.korea.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.insightkorea.korea.model.Employee;

public class EmployeeValidator implements Validator {

	private static Logger logger = LoggerFactory.getLogger(EmployeeValidator.class);
	private static final String REQUIRED = "required";

    @Override
    public void validate(Object obj, Errors errors) {
        Employee employee = (Employee) obj;
        logger.debug(employee.toString());
        String name = employee.getName();
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        if (!StringUtils.hasLength(employee.getEmployeeId())) {
            errors.rejectValue("id", REQUIRED, REQUIRED);
        }
    }

    /**
     * This Validator validates *just* Pet instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }


}
