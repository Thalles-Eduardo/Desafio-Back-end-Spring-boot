package br.com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.demo.model.UserModel;
import br.com.demo.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public Optional<UserModel> findById(Long id){
        return repository.findById(id);
    }

    
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public Boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }


    public UserModel save(UserModel user) {
        return repository.save(user);
    }

    public List<UserModel> createAndFindAll(UserModel user){
        repository.save(user);
        return repository.findAll();
    }

    public List<UserModel> updateAndFindAll(UserModel user){
        repository.save(user);
        return repository.findAll();
    }

    public List<UserModel> list(){
        return repository.findAll();
    }

}
