package br.com.brasilprev.register.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.register.login.model.Login;


public interface LoginRepository extends JpaRepository<Login, Long> {
	
	Optional<Login> findByEmail(String email);

}
