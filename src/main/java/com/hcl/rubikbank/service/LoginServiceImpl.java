package com.hcl.rubikbank.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.rubikbank.dto.LoginResponseDto;
import com.hcl.rubikbank.entity.Customer;
import com.hcl.rubikbank.exception.CommonException;
import com.hcl.rubikbank.repository.CustomerRepository;
import com.hcl.rubikbank.util.RubibankConstants;

@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	CustomerRepository customerRepository;

	public LoginResponseDto userLogin(Integer customerId) {
		LoginResponseDto response = new LoginResponseDto();
		logger.info("inside the userLogin method..");
		Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
		if (!customer.isPresent())
			throw new CommonException(RubibankConstants.CUSTOMER_ID_NOT_FOUND);
		response.setCustomerId(customer.get().getCustomerId());
		response.setMessage("Customer Login success");
		return response;
	}
}
