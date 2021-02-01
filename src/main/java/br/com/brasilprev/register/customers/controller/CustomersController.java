package br.com.brasilprev.register.customers.controller;

import java.net.URI;
import java.util.ArrayList;
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

import br.com.brasilprev.application.customers.command.RecoverCustomerAll;
import br.com.brasilprev.application.customers.command.RecoverCustomerCPF;
import br.com.brasilprev.application.customers.command.SaveCustomer;
import br.com.brasilprev.application.customers.dto.CustomerDTO;
import br.com.brasilprev.infra.customers.RepositoryCustomersMysql;
import br.com.brasilprev.register.customers.form.CustomersForm;
import br.com.brasilprev.register.customers.form.CustomersUpdateForm;

@RestController	
@RequestMapping("/customers")
public class CustomersController {
	
	
	@PostMapping
	public ResponseEntity<CustomerDTO> insert(@RequestBody @Valid CustomersForm customersForm, UriComponentsBuilder urBuilder) {
		
		CustomerDTO customerDTO = new CustomerDTO();
		
		SaveCustomer saveCustomer = new SaveCustomer(new RepositoryCustomersMysql("brasil-prev"));
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
	public ResponseEntity<CustomerDTO> update(@RequestBody @Valid CustomersUpdateForm customersForm, UriComponentsBuilder urBuilder) {
		
		CustomerDTO customerDTO = new CustomerDTO();
		
		SaveCustomer saveCustomer = new SaveCustomer(new RepositoryCustomersMysql("brasil-prev"));
		saveCustomer.execute(customerDTO);
		
		
		URI uri = urBuilder.path("/customers/{cpf}").buildAndExpand(customerDTO.getCpf()).toUri();
		return ResponseEntity.created(uri).body(customerDTO);
		
	}
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<?> delete(@PathVariable(required = true) String cpf) {
	
		return ResponseEntity.notFound().build();		
		
	}
	
	

}
