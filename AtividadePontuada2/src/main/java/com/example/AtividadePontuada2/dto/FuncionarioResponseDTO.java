package com.example.AtividadePontuada2.dto;

public class FuncionarioResponseDTO {
    private String nome;
    private String email;
    private String setor;
    private Double salario;

    // Construtor padrão
    public FuncionarioResponseDTO() {
    }

    // Construtor completo (usado na FuncionarioService)
    public FuncionarioResponseDTO(String nome, String email, String setor, Double salario) {
        this.nome = nome;
        this.email = email;
        this.setor = setor;
        this.salario = salario;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}