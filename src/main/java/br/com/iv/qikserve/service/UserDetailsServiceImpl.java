package br.com.iv.qikserve.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.iv.qikserve.model.ClientModel;
import br.com.iv.qikserve.repository.ClientRepository;
import br.com.iv.qikserve.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientService cliService;
	
	@Override
	public UserDetails loadUserByUsername(String login)throws UsernameNotFoundException {
		Optional<ClientModel> usr = clientRepo.findByLogin(login);
		
		if(!usr.isPresent())
			throw new UsernameNotFoundException(login);
		
		
		return new UserSS(usr.get().getId(), usr.get().getLogin(), usr.get().getPassword(), cliService.getClientLastBasket(usr.get()));
	}

}
