package com.example.AtividadePontuada2.dto;

public class ClienteResponseDTO {
    private String nome;
    private String email;
    private String datan;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(String nome, String email, String datan) {
        this.nome = nome;
        this.email = email;
        this.datan = datan;
    }


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

    public String getDatan() {
        return datan;
    }

    public void setDatan(String datan) {
        this.datan = datan;
    }
}
