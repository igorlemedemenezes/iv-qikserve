package br.com.iv.qikserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iv.qikserve.model.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Integer>{
	Optional<ClientModel> findByLogin(String login);
}
