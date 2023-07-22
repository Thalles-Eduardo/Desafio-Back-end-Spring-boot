package br.com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.demo.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
