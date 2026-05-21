package com.example.AtividadePontuada2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tab_cliente")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,length = 200)
    private String nome;

    @Column(nullable = false,unique = true)
    private String cpf;

    @Column(nullable = false)
    private String datan;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;


    public ClienteModel() {
    }

    public ClienteModel(Long id, String nome, String cpf, String datan, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.datan = datan;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDatan() {
        return datan;
    }

    public void setDatan(String datan) {
        this.datan = datan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
