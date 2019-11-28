package com.example.trabalhodsd.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @PrimaryKey
    private int id_user;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "list_tarefas")
    private List<Tarefa> tarefas;

    public User(int id, String nome, List<Tarefa> tarefas) {
        this.id_user = id;
        this.nome = nome;
        this.tarefas = tarefas;
    }

    public User() {
        this.tarefas = new ArrayList<Tarefa>();
    }

    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
