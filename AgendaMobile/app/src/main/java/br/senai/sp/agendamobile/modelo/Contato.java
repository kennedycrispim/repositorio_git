package br.senai.sp.agendamobile.modelo;

import java.io.Serializable;

public class Contato implements Serializable{
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String  enderecolikedin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnderecolikedin() {
        return enderecolikedin;
    }

    public void setEnderecolikedin(String enderecolikedin) {
        this.enderecolikedin = enderecolikedin;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
}
