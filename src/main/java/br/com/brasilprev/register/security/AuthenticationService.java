package br.com.brasilprev.register.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.brasilprev.register.login.model.Login;
import br.com.brasilprev.register.login.repository.LoginRepository;



@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private LoginRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Login> login = repository.findByEmail(username);
		if (login.isPresent()) {
			return login.get();
		}		
		
		throw new UsernameNotFoundException("Invalid detail!");
	}

}
