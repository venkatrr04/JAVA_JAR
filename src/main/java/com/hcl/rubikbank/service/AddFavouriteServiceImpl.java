package com.hcl.rubikbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.rubikbank.dto.AddFavouriteRequestDto;
import com.hcl.rubikbank.dto.AddFavouriteResponseDto;
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
		if (bankNumber.length() != 20)
			throw new CommonException(RubibankConstants.ERROR_IBAN_NUMBER);

		Favourite favourite = new Favourite();
		favourite.setAccountName(addFavouriteRequestDto.getAccountName());
		favourite.setAccountNumber(bankNumber);
		favourite.setAccountStatus("1");
		favourite.setBankId(addFavouriteRequestDto.getBankId());
		favourite.setCustomerId(addFavouriteRequestDto.getCustomerId());
		favaouriteRepository.save(favourite);

		return new AddFavouriteResponseDto(RubibankConstants.ADD_SUCCESS);
	}

}
