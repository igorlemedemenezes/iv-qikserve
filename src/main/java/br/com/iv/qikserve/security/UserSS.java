package br.com.iv.qikserve.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.iv.qikserve.model.BasketModel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String login;
	private String password;
	private BasketModel basket;

	public UserSS(Integer id, String login, String senha, BasketModel basket) {
		this.id = id;
		this.login = login;
		this.password = senha;
		this.basket = basket;
	}

	
	public Integer getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}
	
	public BasketModel getBasket() {
		return basket;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
