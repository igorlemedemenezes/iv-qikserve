package br.com.iv.qikserve.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.iv.qikserve.model.ClientModel;
import lombok.Getter;


@Getter
public class ClientDTO {

	private String login;
	private String password;

	public ClientModel retornaModelo(BCryptPasswordEncoder pe) {
		
		ClientModel cli = new ClientModel();
		
		cli.setLogin(getLogin());
		cli.setPassword(pe.encode(getPassword()));
		
		return cli;
	}
	
}
