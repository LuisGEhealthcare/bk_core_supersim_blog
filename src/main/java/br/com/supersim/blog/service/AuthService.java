package br.com.supersim.blog.service;

import org.springframework.http.ResponseEntity;

import br.com.supersim.blog.entity.Login;
import br.com.supersim.blog.entity.Token;

public interface AuthService {
	
	public ResponseEntity<Token> login(Login login);

}
