package br.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.demo.model.TransactionModel;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long>{
    
}
