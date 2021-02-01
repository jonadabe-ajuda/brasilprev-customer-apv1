package br.com.brasilprev.register.customers.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brasilprev.application.customers.command.DeleteCustomer;
import br.com.brasilprev.application.customers.command.RecoverCustomerAll;
import br.com.brasilprev.application.customers.command.RecoverCustomerCPF;
import br.com.brasilprev.application.customers.command.SaveCustomer;
import br.com.brasilprev.application.customers.command.UpdateCustomer;
import br.com.brasilprev.application.customers.dto.CustomerDTO;
import br.com.brasilprev.infra.customers.RepositoryCustomersMysql;
import br.com.brasilprev.register.customers.form.CustomersForm;
import br.com.brasilprev.register.customers.form.CustomersUpdateForm;

@RestController	
@RequestMapping("/customers")
public class CustomersController {
	
	
	@PostMapping
	public ResponseEntity<CustomerDTO> insert(@RequestBody @Valid CustomersForm customersForm, UriComponentsBuilder urBuilder) {
		
		
		
		SaveCustomer saveCustomer = new SaveCustomer(new RepositoryCustomersMysql("brasil-prev"));
		CustomerDTO customerDTO = new CustomerDTO(customersForm.getName(), 
												  customersForm.getCpf(),
												  customersForm.getStreet(),
												  customersForm.getNumber(),
												  customersForm.getDistrict(),
												  customersForm.getCity(),
												  customersForm.getState(), 
												  customersForm.getZipcode());
		saveCustomer.execute(customerDTO);
		
		URI uri = urBuilder.path("/customers/{cpf}").buildAndExpand(customerDTO.getCpf()).toUri();
		return ResponseEntity.created(uri).body(customerDTO);
		
	}
	
	@GetMapping
	public List<CustomerDTO> all() {
		RecoverCustomerAll recoverCustomerAll = new RecoverCustomerAll(new RepositoryCustomersMysql("brasil-prev"));
		return recoverCustomerAll.execute();
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<CustomerDTO> recoverWithCPF(@PathVariable(required = true) String cpf) {
		
		RecoverCustomerCPF recoverCustomerCPF = new RecoverCustomerCPF(new RepositoryCustomersMysql("brasil-prev"));
		CustomerDTO customerDTO = recoverCustomerCPF.execute(cpf);
		
		if (customerDTO != null) {
			return ResponseEntity.ok(customerDTO);
		}
	
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<CustomerDTO> update(@RequestBody @Valid CustomersUpdateForm customersUpdateForm, @PathVariable(required = true) String cpf, UriComponentsBuilder urBuilder) {
		
		
		UpdateCustomer updateCustomer = new UpdateCustomer(new RepositoryCustomersMysql("brasil-prev"));
		CustomerDTO customerDTO = new CustomerDTO(customersUpdateForm.getName(),
													cpf, 
													customersUpdateForm.getStreet(),
													customersUpdateForm.getNumber(),
													customersUpdateForm.getDistrict(),
													customersUpdateForm.getCity(),
													customersUpdateForm.getState(), 
													customersUpdateForm.getZipcode());		
		updateCustomer.execute(customerDTO);
		
		
		URI uri = urBuilder.path("/customers/{cpf}").buildAndExpand(customerDTO.getCpf()).toUri();
		return ResponseEntity.created(uri).body(customerDTO);
		
	}
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<?> delete(@PathVariable(required = true) String cpf) {
	
		DeleteCustomer deleteCustomerCPF = new DeleteCustomer(new RepositoryCustomersMysql("brasil-prev"));
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCpf(cpf);
		deleteCustomerCPF.execute(customerDTO);
		
		return ResponseEntity.ok(customerDTO);

		
	}
	
	

}
