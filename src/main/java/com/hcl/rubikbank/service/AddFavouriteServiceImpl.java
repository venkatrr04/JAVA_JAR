package com.hcl.rubikbank.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.rubikbank.dto.AddFavouriteRequestDto;
import com.hcl.rubikbank.dto.AddFavouriteResponseDto;
import com.hcl.rubikbank.dto.BankDto;
import com.hcl.rubikbank.entity.Favourite;
import com.hcl.rubikbank.exception.CommonException;
import com.hcl.rubikbank.repository.FavouriteRepository;
import com.hcl.rubikbank.util.RubibankConstants;

/**
 * 
 * @author HariPriya G This class intended to adding the favourite details of
 *         customer
 *
 */
@Service
public class AddFavouriteServiceImpl implements AddFavouriteServcie {

	@Autowired
	FavouriteRepository favaouriteRepository;
	@Autowired
	RestTemplate restTemplate;

	/**
	 * 
	 * This method intended to adding the favourite details of customer
	 * 
	 * @param AddFavouriteRequestDto is the input object it contains
	 *                               accountName,bankName and bankCode
	 * @return it returns AddFavouriteResponseDto object with message
	 */

	@Override
	public AddFavouriteResponseDto addFaourite(AddFavouriteRequestDto addFavouriteRequestDto) {

		String bankNumber = addFavouriteRequestDto.getAccountNumber();
		String bankCodeStr = bankNumber.substring(4, 8);
		if (bankNumber.length() != 20)
			throw new CommonException(RubibankConstants.ERROR_IBAN_NUMBER);
//		ResponseEntity<BankDto> bankDetails = getBankDetails(bankCodeStr);
//		if (!bankDetails.getBody().getBankName().equalsIgnoreCase(addFavouriteRequestDto.getBankName()))
//			throw new CommonException(RubibankConstants.ERROR_BANK_NAME_EXIST);

		Favourite favourite = new Favourite();
		favourite.setAccountName(addFavouriteRequestDto.getAccountName());
		favourite.setAccountNumber(bankNumber);
		favourite.setAccountStatus("1");
//		favourite.setBankId(bankDetails.getBody().getBankId());
		favourite.setBankId(addFavouriteRequestDto.getBankId());
		favourite.setCustomerId(addFavouriteRequestDto.getCustomerId());
		favaouriteRepository.save(favourite);

		return new AddFavouriteResponseDto(RubibankConstants.ADD_SUCCESS );
	}

	public ResponseEntity<BankDto> getBankDetails(String bankCode) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		return new ResponseEntity<BankDto>(restTemplate
				.exchange(RubibankConstants.BANK_DETAILS_URL + bankCode, HttpMethod.GET, entity, BankDto.class)
				.getBody(), HttpStatus.OK);

	}

}
