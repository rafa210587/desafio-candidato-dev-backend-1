package com.srmasset.thcepdetails.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.srmasset.thcepdetails.pojos.AddressDTO;


/**
 * Just a example if Feign should be used
 * API and receives information about the zipcode requested
 * 
 * @author Rafael Meira
 * @author https://www.linkedin.com/in/rafael-meira-28876174/
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@FeignClient(url = "${thcepdetails.endpoint.th-api}", name = "ZipCode")
public interface ZipCodeApi {
	
   @GetMapping("/{cep}")
   public AddressDTO findById(@PathVariable(value="cep") String cep);
}

