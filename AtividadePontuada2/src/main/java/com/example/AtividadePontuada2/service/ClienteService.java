package com.example.AtividadePontuada2.service;

import com.example.AtividadePontuada2.dto.ClienteRequestDTO;
import com.example.AtividadePontuada2.dto.ClienteResponseDTO;
import com.example.AtividadePontuada2.model.ClienteModel;
import com.example.AtividadePontuada2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<ClienteResponseDTO> listarTodos() {
        return repository
                .findAll()
                .stream()
                .map(c -> new ClienteResponseDTO(
                        c.getNome(),
                        c.getEmail(),
                        c.getDatan()
                ))
                .toList();
    }


    public ClienteResponseDTO salvarCliente(ClienteRequestDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Cliente já cadastrado");
        }

        if (repository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("Cliente já cadastrado com este CPF");
        }


        ClienteModel novoCliente = new ClienteModel();
        novoCliente.setNome(dto.getNome());
        novoCliente.setEmail(dto.getEmail());
        novoCliente.setDatan(dto.getDatan());
        novoCliente.setCpf(dto.getCpf());


        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        novoCliente.setSenha(senhaCriptografada);


        repository.save(novoCliente);

        return new ClienteResponseDTO(
                novoCliente.getNome(),
                novoCliente.getEmail(),
                novoCliente.getDatan()
        );
    }
    public void deletarCliente(Long id) {
        ClienteModel cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado:"));

        repository.delete(cliente);

    }


    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {

        ClienteModel clienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado "));


        clienteExistente.setNome(dto.getNome());
        clienteExistente.setEmail(dto.getEmail());
        clienteExistente.setDatan(dto.getDatan());


        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            clienteExistente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }


        repository.save(clienteExistente);

        return new ClienteResponseDTO(
                clienteExistente.getNome(),
                clienteExistente.getEmail(),
                clienteExistente.getDatan()
        );
    }
}