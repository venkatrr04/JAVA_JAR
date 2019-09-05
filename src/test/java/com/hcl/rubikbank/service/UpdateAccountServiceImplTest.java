package com.hcl.rubikbank.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.rubikbank.dto.UpdateAccountRequestDto;
import com.hcl.rubikbank.dto.UpdateAccountResponseDto;
import com.hcl.rubikbank.entity.Favourite;
import com.hcl.rubikbank.repository.FavouriteRepository;

@RunWith(MockitoJUnitRunner.class)
public class UpdateAccountServiceImplTest {
	
	
	@InjectMocks
	UpdateAccountServiceImpl updateAccountServiceImpl;
	
	@Mock
	FavouriteRepository favouriteRepository;
	
	
	UpdateAccountResponseDto updateAccountResponseDto;
	UpdateAccountRequestDto updateAccountRequestDto;
	Favourite favourite;
	
	public Favourite getFavourite()
	{
		Favourite favourite = new Favourite();
		favourite.setAccountNumber("ES121234123412341234");
		favourite.setAccountName("Bridge Foundation");
		favourite.setAccountStatus("1");
		return favourite;
	}

}
