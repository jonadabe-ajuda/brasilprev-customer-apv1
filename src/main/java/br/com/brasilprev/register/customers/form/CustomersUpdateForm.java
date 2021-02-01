package br.com.brasilprev.register.customers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CustomersUpdateForm {

	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String name;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String street;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String district;

	@NotNull @NotEmpty @Length(min = 5, max = 100)	
	private String city;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String state;
	
	@NotNull @NotEmpty @Length(min = 8, max = 8)
	private String zipcode;
	
	
	
	
	
	
}
