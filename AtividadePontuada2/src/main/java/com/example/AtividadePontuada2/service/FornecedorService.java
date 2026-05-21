package com.example.AtividadePontuada2.service;

import com.example.AtividadePontuada2.dto.FornecedorRequestDTO;
import com.example.AtividadePontuada2.dto.FornecedorResponseDTO;
import com.example.AtividadePontuada2.model.FornecedorModel;
import com.example.AtividadePontuada2.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;


    public List<FornecedorResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(f -> new FornecedorResponseDTO(
                        f.getNome(),
                        f.getEmail(),
                        f.getTelefone()
                ))
                .toList();
    }


    public FornecedorResponseDTO buscarPorId(Long id) {
        FornecedorModel fornecedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));

        return new FornecedorResponseDTO(
                fornecedor.getNome(),
                fornecedor.getEmail(),
                fornecedor.getTelefone()
        );
    }


    public void salvarFornecedor(FornecedorRequestDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Fornecedor já cadastrado com este e-mail");
        }

        if (repository.findByCnpj(dto.getCnpj()).isPresent()) {
            throw new RuntimeException("Fornecedor já cadastrado com este CNPJ");
        }

        FornecedorModel novoFornecedor = new FornecedorModel();
        novoFornecedor.setNome(dto.getNome());
        novoFornecedor.setCnpj(dto.getCnpj());
        novoFornecedor.setEmail(dto.getEmail());
        novoFornecedor.setTelefone(dto.getTelefone());

        repository.save(novoFornecedor);
    }


    public void atualizarFornecedor(Long id, FornecedorRequestDTO dto) {
        FornecedorModel fornecedorExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));

        repository.findByEmail(dto.getEmail()).ifPresent(f -> {
            if (!f.getId().equals(id)) {
                throw new RuntimeException("Este e-mail já está em uso por outro fornecedor");
            }
        });

        repository.findByCnpj(dto.getCnpj()).ifPresent(f -> {
            if (!f.getId().equals(id)) {
                throw new RuntimeException("Este CNPJ já está em uso por outro fornecedor");
            }
        });

        fornecedorExistente.setNome(dto.getNome());
        fornecedorExistente.setCnpj(dto.getCnpj());
        fornecedorExistente.setEmail(dto.getEmail());
        fornecedorExistente.setTelefone(dto.getTelefone());

        repository.save(fornecedorExistente);
    }


    public void deletarFornecedor(Long id) {
        FornecedorModel fornecedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + id));

        repository.delete(fornecedor);
    }
}