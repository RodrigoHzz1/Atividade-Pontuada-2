package com.example.AtividadePontuada2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClienteRequestDTO {

        @NotBlank(message = "O nome é Obrigatório")
        @Size(max = 200,message = "O nome deve ter no máximo 200 caracteres")
        private String nome;

        @NotBlank(message = "O cpf é Obrigatório")
        private String cpf;

        @NotBlank(message = "O email é Obrigatório")
        @Email(message = "Insira um email valído")
        private String email;

        @NotBlank(message = "A Data de Nascimento é Obrigatória")
        private String datan;

        @NotBlank(message = "informe uma senha valida")
        @Size(min = 1 , max = 10, message = "A senha deve ter de 1 a 10 caracteres")
        private String senha;


        public ClienteRequestDTO() {
        }

        public ClienteRequestDTO(String nome, String cpf, String email, String datan, String senha) {
                this.nome = nome;
                this.cpf = cpf;
                this.email = email;
                this.datan = datan;
                this.senha = senha;
        }

        public @NotBlank(message = "O nome é Obrigatório") @Size(max = 200, message = "O nome deve ter no máximo 200 caracteres") String getNome() {
                return nome;
        }

        public void setNome(@NotBlank(message = "O nome é Obrigatório") @Size(max = 200, message = "O nome deve ter no máximo 200 caracteres") String nome) {
                this.nome = nome;
        }

        public @NotBlank(message = "O cpf é Obrigatório") String getCpf() {
                return cpf;
        }

        public void setCpf(@NotBlank(message = "O cpf é Obrigatório") String cpf) {
                this.cpf = cpf;
        }

        public @NotBlank(message = "O email é Obrigatório") @Email(message = "Insira um email valído") String getEmail() {
                return email;
        }

        public void setEmail(@NotBlank(message = "O email é Obrigatório") @Email(message = "Insira um email valído") String email) {
                this.email = email;
        }

        public @NotBlank(message = "A Data de Nascimento é Obrigatória") String getDatan() {
                return datan;
        }

        public void setDatan(@NotBlank(message = "A Data de Nascimento é Obrigatória") String datan) {
                this.datan = datan;
        }

        public @NotBlank(message = "informe uma senha valida") @Size(min = 1, max = 10, message = "A senha deve ter de 1 a 10 caracteres") String getSenha() {
                return senha;
        }

        public void setSenha(@NotBlank(message = "informe uma senha valida") @Size(min = 1, max = 10, message = "A senha deve ter de 1 a 10 caracteres") String senha) {
                this.senha = senha;
        }
}
