package com.example.AtividadePontuada2.service;

import com.example.AtividadePontuada2.dto.FuncionarioRequestDTO;
import com.example.AtividadePontuada2.dto.FuncionarioResponseDTO;
import com.example.AtividadePontuada2.model.FuncionarioModel;
import com.example.AtividadePontuada2.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    // READ - Listar todos os funcionários
    public List<FuncionarioResponseDTO> listarTodos() {
        return repository
                .findAll()
                .stream()
                .map(f -> new FuncionarioResponseDTO(
                        f.getNome(),
                        f.getEmail(),
                        f.getSetor(),
                        f.getSalario()
                ))
                .toList();
    }

    // READ - Buscar um funcionário específico por ID
    public FuncionarioResponseDTO buscarPorId(Long id) {
        FuncionarioModel funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + id));

        return new FuncionarioResponseDTO(
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getSetor(),
                funcionario.getSalario()
        );
    }

    // CREATE - Salvar funcionário com validações de E-mail e CPF
    public void salvarFuncionario(FuncionarioRequestDTO dto) {
        // 1. Validação de E-mail único
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Funcionário já cadastrado com este e-mail");
        }

        // 2. Validação de CPF único
        if (repository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("Funcionário já cadastrado com este CPF");
        }

        // 3. Transferência do DTO para a Model
        FuncionarioModel novoFuncionario = new FuncionarioModel();
        novoFuncionario.setNome(dto.getNome());
        novoFuncionario.setEmail(dto.getEmail());
        novoFuncionario.setCpf(dto.getCpf());
        novoFuncionario.setTelefone(dto.getTelefone());
        novoFuncionario.setSetor(dto.getSetor());
        novoFuncionario.setSalario(dto.getSalario());

        repository.save(novoFuncionario);
    }

    // UPDATE - Atualizar dados do funcionário blindando contra e-mail/CPF duplicados
    public FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO dto) {
        // 1. Busca o funcionário existente
        FuncionarioModel funcionarioExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + id));

        // 2. Se o e-mail mudou, valida se já não pertence a outra pessoa
        repository.findByEmail(dto.getEmail()).ifPresent(f -> {
            if (!f.getId().equals(id)) {
                throw new RuntimeException("Este e-mail já está sendo usado por outro funcionário");
            }
        });

        // 3. Se o CPF mudou, valida se já não pertence a outra pessoa
        repository.findByCpf(dto.getCpf()).ifPresent(f -> {
            if (!f.getId().equals(id)) {
                throw new RuntimeException("Este CPF já está sendo usado por outro funcionário");
            }
        });

        // 4. Atualiza os dados na Model
        funcionarioExistente.setNome(dto.getNome());
        funcionarioExistente.setEmail(dto.getEmail());
        funcionarioExistente.setCpf(dto.getCpf());
        funcionarioExistente.setTelefone(dto.getTelefone());
        funcionarioExistente.setSetor(dto.getSetor());
        funcionarioExistente.setSalario(dto.getSalario());

        // 5. Salva no banco de dados
        repository.save(funcionarioExistente);

        // 6. Retorna o DTO de resposta atualizado
        return new FuncionarioResponseDTO(
                funcionarioExistente.getNome(),
                funcionarioExistente.getEmail(),
                funcionarioExistente.getSetor(),
                funcionarioExistente.getSalario()
        );
    }

    // DELETE - Deletar funcionário por ID
    public void deletarFuncionario(Long id) {
        FuncionarioModel funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o ID: " + id));

        repository.delete(funcionario);
    }
}