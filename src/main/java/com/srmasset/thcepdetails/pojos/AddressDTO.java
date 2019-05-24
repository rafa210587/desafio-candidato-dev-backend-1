package com.srmasset.thcepdetails.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an Address.
 * 
 * @author Rafael Meira
 * @author https://www.linkedin.com/in/rafael-meira-28876174/
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	@JsonInclude(Include.NON_NULL)
	private String msg;

	@JsonInclude(Include.NON_NULL)
	private String estado;

	@JsonInclude(Include.NON_NULL)
	private String cidade;

	@JsonInclude(Include.NON_NULL)
	private String bairro;

	@JsonInclude(Include.NON_NULL)
	private String logradouro;

}
