package com.srmasset.thcepdetails.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srmasset.thcepdetails.pojos.AddressDTO;

import lombok.AllArgsConstructor;

@RunWith(SpringRunner.class)
@SpringBootTest
@AllArgsConstructor
public class CepDetailControllerTest {

	private THCepDetailController tHcepDetailController;

	@Test
	public void findCep() throws ParseException {
		String cep = "03429-000";

		AddressDTO address = tHcepDetailController.findCep(cep);

		assertNotNull(address);
	}

	@Test
	public void invalidCep() throws ParseException {
		String cep = "DQDOWdow00";

		AddressDTO address = tHcepDetailController.findCep(cep);

		assertNull(address);
	}

	@Test
	public void findCeps() throws ParseException {
		Collection<String> ceps = new ArrayList<>();

		List<AddressDTO> addresses = tHcepDetailController.findCep(ceps);

		assertNotNull(addresses);
	}
}