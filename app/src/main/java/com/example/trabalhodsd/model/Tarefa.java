package com.example.trabalhodsd.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

//chave estrangeira para usuario
@Entity
public class Tarefa {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id_tarefa;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "data_inicio")
    private String data_inicio;

    @ColumnInfo(name = "data_fim")
    private String data_fim;

    public Tarefa(String titulo, String data, String data_inicio, String data_fim) {
        this.titulo = titulo;
        this.data = data;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public int getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id_tarefa=" + id_tarefa +
                ", titulo='" + titulo + '\'' +
                ", data='" + data + '\'' +
                ", data_inicio='" + data_inicio + '\'' +
                ", data_fim='" + data_fim + '\'' +
                '}';
    }
}
