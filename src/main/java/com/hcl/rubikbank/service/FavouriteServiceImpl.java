package com.hcl.rubikbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hcl.rubikbank.dto.FavouriteResponseDto;
import com.hcl.rubikbank.entity.BankData;
import com.hcl.rubikbank.entity.Customer;
import com.hcl.rubikbank.entity.Favourite;
import com.hcl.rubikbank.exception.CommonException;
import com.hcl.rubikbank.repository.BankDataRepository;
import com.hcl.rubikbank.repository.CustomerRepository;
import com.hcl.rubikbank.repository.FavouriteRepository;
import com.hcl.rubikbank.util.RubikConstants;

/**
 * @author DeepikaSivarajan . This is the service class for getting
 *         favouriteAccounts
 * 
 *
 */
@Service
public class FavouriteServiceImpl implements FavouriteService {
	private static final Logger logger = LoggerFactory.getLogger(FavouriteServiceImpl.class);
	@Autowired
	FavouriteRepository favouriteRepository;
	@Autowired
	BankDataRepository bankDataRepository;
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * This method is intended to display favorite accounts
	 * 
	 * @param pageCount is the input parameter(from UI)
	 * @return FavouriteResponseDto is the return object which includes
	 *         accountName,accountNumber,bankName,loginId
	 * @exception FAVOURITES_NOT_FOUND if favorites are not found
	 */
	@Override
	public List<FavouriteResponseDto> getFavoriteAccounts(Integer pageCount) {
		logger.info("favorite accounts in service");
		List<FavouriteResponseDto> favourites = new ArrayList<>();
		Pageable pageable = (Pageable) PageRequest.of(pageCount, 5);
		Page<Favourite> favouriteAccounts = favouriteRepository.findAll(pageable);
		if (favouriteAccounts.isEmpty())
			throw new CommonException(RubikConstants.FAVOURITES_NOT_FOUND);
		favouriteAccounts.stream().forEach(f -> {

			BankData bankData = bankDataRepository.findByBankId(f.getBankId());
			Optional<Customer> customer = customerRepository.findByCustomerId(f.getCustomerId());
			FavouriteResponseDto favouriteResponseDto = FavouriteResponseDto.builder().accountName(f.getAccountName())
					.accountNumber(f.getAccountNumber()).bankName(bankData.getBankName()).customerId(customer.get().getCustomerId())
					.build();
			favourites.add(favouriteResponseDto);

		});
		return favourites;

	}

}
