package com.srmasset.thcepdetails.service;

import java.text.ParseException;
import java.time.LocalDateTime;

import javax.swing.text.MaskFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.srmasset.thcepdetails.pojos.AddressDTO;

import lombok.AllArgsConstructor;

/**
 * business class for the THCepDetailcontroller sends a requisition to the test
 * API and receives information about the zipcode requested
 * 
 * @author Rafael Meira
 * @author https://www.linkedin.com/in/rafael-meira-28876174/
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class THCepService {

	private static final Logger LOGGER = LoggerFactory.getLogger(THCepService.class);

	private RestTemplate restTemplate;

	private THCepDetailsProperties restProperties;

	/**
	 * makes a request to the test api passing a zipcode using the exchange method
	 * 
	 * @param zipCode a String containing the zipcode requested
	 * @return an Address object containing the information required
	 * @throws ParseException
	 */
	@Cacheable("findAddress")
	@HystrixCommand(fallbackMethod = "reliable")
	public AddressDTO findAddress(String zipCode) throws ParseException {
		String request = zipCode;
		String thApi = restProperties.getEndpoint().getThApi();

		LOGGER.info("Searching zipcode {} in {}", formatZipCode(request), LocalDateTime.now());
		ResponseEntity<AddressDTO> response = restTemplate.exchange(thApi + zipCode, HttpMethod.GET,
				new HttpEntity<>(request), AddressDTO.class);

		if (response.getBody().getBairro() == null && response.getBody().getEstado() == null
				&& response.getBody().getCidade() == null && response.getBody().getLogradouro() == null) {
			LOGGER.info("Zipcode {} not found", formatZipCode(request));
			response.getBody().setMsg("Cep não encontrado");
		}
		LOGGER.info("Zipcode {} found at {}", formatZipCode(request), LocalDateTime.now());
		return response.getBody();
	}

	/**
	 * Hystrix failsafe method
	 * 
	 * @param zipCode a String containing the zipcode requested
	 * @return an Address object contendo a msg about the failback
	 * @throws ParseException
	 */
	public AddressDTO reliable(String zipCode) {
		AddressDTO address = new AddressDTO();
		address.setMsg("A Api que busca o CEP está fora do ar");
		return address;
	}

	/**
	 * just formatter the zipCode to exhibit in the log
	 * 
	 * @param zipCode a String containing the zipcode requested
	 * @return a String containing the zipcode formated
	 * @throws ParseException
	 */
	public String formatZipCode(String zipCode) throws ParseException {

		MaskFormatter f = new MaskFormatter("#####-###");
		f.setValueContainsLiteralCharacters(false);
		String formatedZipCode = f.valueToString(zipCode).trim();
		if (formatedZipCode.endsWith("-")) {
			formatedZipCode = formatedZipCode.substring(0, formatedZipCode.length() - 1);
		}
		return formatedZipCode;

	}
}