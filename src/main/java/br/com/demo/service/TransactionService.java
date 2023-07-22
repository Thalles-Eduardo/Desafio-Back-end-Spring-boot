package br.com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.demo.model.TransactionModel;
import br.com.demo.repository.TransactionRepository;

@Service
public class TransactionService {

    public TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionModel save(TransactionModel transactionModel) {
        return transactionRepository.save(transactionModel);
    }

    public List<TransactionModel> list(){
        return transactionRepository.findAll();
    }
    
}
