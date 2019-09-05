package com.hcl.rubikbank.service;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.rubikbank.dto.BankDto;
import com.hcl.rubikbank.dto.UpdateAccountRequestDto;
import com.hcl.rubikbank.dto.UpdateAccountResponseDto;
import com.hcl.rubikbank.entity.Favourite;
import com.hcl.rubikbank.exception.CommonException;
import com.hcl.rubikbank.repository.FavouriteRepository;
import com.hcl.rubikbank.util.RubibankConstants;
import com.hcl.rubikbank.util.RubikConstants;

/**
 * @author Gurpreet Singh This class is use to update the favorite account.
 *
 */
@Service
public class UpdateAccountServiceImpl implements UpdateAccountService {

	private static final Logger logger = LoggerFactory.getLogger(UpdateAccountServiceImpl.class);

	@Autowired
	FavouriteRepository favouriteRepository;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * @param Integer favouriteId, UpdateAccountRequestDto updateAccountRequestDto
	 *                which includes accountName, accountNumber are the input
	 *                parameters.
	 * 
	 * @return UpdateAccountResponseDto which contains message with status code.
	 * @exception ERROR_NO_FAVOURITE_ACCOUNT, ERROR_IBAN_NUMBER
	 *
	 */

	@Override
	public UpdateAccountResponseDto updateAccount(Integer favouriteId,
			UpdateAccountRequestDto updateAccountRequestDto) {

		logger.info("in update account");
		Optional<Favourite> favourite = favouriteRepository.findByFavouriteId(favouriteId);
		if (!favourite.isPresent())
			throw new CommonException(RubikConstants.ERROR_NO_FAVOURITE_ACCOUNT);
		logger.trace("no favourite account found");

		String bankNumber = updateAccountRequestDto.getAccountNumber();
		if (bankNumber.length() != 20)
			throw new CommonException(RubibankConstants.ERROR_IBAN_NUMBER);

		favourite.get().setAccountName(updateAccountRequestDto.getAccountName());
		favourite.get().setAccountNumber(bankNumber);
		favourite.get().setBankId(updateAccountRequestDto.getBankId());
		favouriteRepository.save(favourite.get());

		return new UpdateAccountResponseDto(RubikConstants.EDIT_SUCCESS);

	}

	public ResponseEntity<BankDto> getBankDetails(String bankCode) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		return new ResponseEntity<>(restTemplate
				.exchange(RubikConstants.BANK_DETAILS_URL + bankCode, HttpMethod.GET, entity, BankDto.class).getBody(),
				HttpStatus.OK);

	}

}
