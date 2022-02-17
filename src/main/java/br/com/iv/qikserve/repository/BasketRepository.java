package br.com.iv.qikserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iv.qikserve.model.BasketModel;

@Repository
public interface BasketRepository extends JpaRepository<BasketModel, Integer>{
}
