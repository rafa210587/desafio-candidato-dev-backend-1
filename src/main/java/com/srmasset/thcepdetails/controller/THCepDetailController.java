package com.srmasset.thcepdetails.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srmasset.thcepdetails.pojos.AddressDTO;
import com.srmasset.thcepdetails.service.THCepService;

import lombok.AllArgsConstructor;

/**
 * Controller class that expect the requests for the RestAPI
 * 
 * @author Rafael Meira
 * @author https://www.linkedin.com/in/rafael-meira-28876174/
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@AllArgsConstructor
@RestController
@RequestMapping("/thcepdetails")
public class THCepDetailController {

	private THCepService cepService;

	/**
	 * Get method that expect a String variable in the path
	 * 
	 * @param String the zipcode that will be search
	 * @return a Address object with information about the zipcode
	 * @throws ParseException
	 */
	@GetMapping(value = "/cep/{cep}", produces = "application/json")
	public AddressDTO findCep(@Valid @PathVariable("cep") String cep) throws ParseException {
		return cepService.findAddress(cep);

	}

	/**
	 * Post method that expect a body in the requisition with a list of String
	 * zipcodes *
	 * 
	 * @param a RequestBody with a list of String zipcode
	 * @return a list of Address objects with information about the zipcode
	 * @throws ParseException
	 */
	@PostMapping(value = "/cep", produces = "application/json")
	public List<AddressDTO> findCep(@RequestBody Collection<String> ceps) throws ParseException {
		List<AddressDTO> addresses = new ArrayList<>();
		for (String cep : ceps) {
			addresses.add(cepService.findAddress(cep));
		}
		return addresses;
	}
}