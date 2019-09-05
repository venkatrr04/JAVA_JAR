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
import com.hcl.rubikbank.entity.BankData;
import com.hcl.rubikbank.entity.Favourite;
import com.hcl.rubikbank.exception.CommonException;
import com.hcl.rubikbank.repository.BankDataRepository;
import com.hcl.rubikbank.repository.FavouriteRepository;
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
	BankDataRepository bankDataRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * @param Integer favouriteId, UpdateAccountRequestDto updateAccountRequestDto are the input parameters. 
	 * 
	 * @return UpdateAccountResponseDto which contains message with status code.
	 * @exception ERROR_NO_FAVOURITE_ACCOUNT, ERROR_BANK_NOT_FOUND
	 *
	 */
	
	@Override
	public UpdateAccountResponseDto updateAccount(Integer favouriteId,
			UpdateAccountRequestDto updateAccountRequestDto) {
		
		logger.info("in update account");
		Optional<Favourite> favourite = favouriteRepository.findByFavouriteId(favouriteId);
		if(!favourite.isPresent())
			throw new CommonException(RubikConstants.ERROR_NO_FAVOURITE_ACCOUNT);
		Optional<BankData> bankdata = bankDataRepository.findById(favourite.get().getBankId());
		if(!bankdata.isPresent())
			throw new CommonException(RubikConstants.ERROR_BANK_NOT_FOUND);
		if(!updateAccountRequestDto.getAccountName().equalsIgnoreCase(favourite.get().getAccountName()))
		{
		throw new CommonException(RubikConstants.ERROR_ACCOUNT_NAME_NOT_FOUND);
		}
		if(!updateAccountRequestDto.getAccountNumber().equalsIgnoreCase(favourite.get().getAccountNumber()))
		{	throw new  CommonException(RubikConstants.ERROR_ACCOUNT_NAME_NOT_FOUND);
		}
		
		ResponseEntity<BankDto> bankDetails = getBankDetails(updateAccountRequestDto.getAccountNumber());
		return new UpdateAccountResponseDto(RubikConstants.ADD_SUCCESS + bankDetails.getBody().getBankName());
		
		
	}

	


	public ResponseEntity<BankDto> getBankDetails(String bankCode) {
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<String> entity = new HttpEntity<>(headers);

	return new ResponseEntity<BankDto>(restTemplate
	.exchange(RubikConstants.BANK_DETAILS_URL + bankCode, HttpMethod.GET, entity, BankDto.class)
	.getBody(), HttpStatus.OK);

	}


	
}
