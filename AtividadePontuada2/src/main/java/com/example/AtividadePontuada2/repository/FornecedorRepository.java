package com.example.AtividadePontuada2.repository;

import com.example.AtividadePontuada2.model.FornecedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorModel, Long> {
    Optional<FornecedorModel> findByEmail (String email);
    Optional<FornecedorModel> findByCnpj (String cnpj);
}
