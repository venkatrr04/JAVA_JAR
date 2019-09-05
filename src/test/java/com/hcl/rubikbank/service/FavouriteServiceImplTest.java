//package com.hcl.rubikbank.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.hcl.rubikbank.dto.FavouriteResponseDto;
//import com.hcl.rubikbank.entity.BankData;
//import com.hcl.rubikbank.entity.Favourite;
//import com.hcl.rubikbank.repository.BankDataRepository;
//import com.hcl.rubikbank.repository.CustomerRepository;
//import com.hcl.rubikbank.repository.FavouriteRepository;
//
//@RunWith(MockitoJUnitRunner.class)
//public class FavouriteServiceImplTest {
//	@Mock
//	FavouriteRepository favouriteRepository;
//	@Mock
//	BankDataRepository bankDataRepository;
//	@Mock
//	CustomerRepository customerRepository;
//	@InjectMocks
//	FavouriteServiceImpl favouriteServiceImpl;
//
//	List<FavouriteResponseDto> responseList;
//	FavouriteResponseDto response;
//	Favourite favourite;
//	BankData bankData;
//
//	@Before
//	public void setUp() {
//		responseList = new ArrayList<>();
//		response = new FavouriteResponseDto();
//		favourite = new Favourite();
//		bankData = new BankData();
//
//	}
//
//	@Test
//	public void testGetFavoriteAccounts() {
//		
//	}
//}
