package br.com.brasilprev.register.customers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CustomersForm {

	@NotNull @NotEmpty @Length(min = 11, max = 11)
	private String cpf;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String name;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String street;
	
	@NotNull
	private Integer number;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String district;

	@NotNull @NotEmpty @Length(min = 5, max = 100)	
	private String city;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String state;
	
	@NotNull @NotEmpty @Length(min = 8, max = 8)
	private String zipcode;

	public String getCpf() {
		return cpf;
	}
	
	public Integer getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getDistrict() {
		return district;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipcode() {
		return zipcode;
	}
	
	
	
	
	
	
}
