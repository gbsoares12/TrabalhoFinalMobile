package com.example.trabalhodsd.model;


//chave estrangeira para usuario

public class Tarefa {

    private int id_tarefa;
    private String nome;
    private String data;

    public Tarefa(String nome, String data) {
        this.nome = nome;
        this.data = data;
    }

    public int getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
