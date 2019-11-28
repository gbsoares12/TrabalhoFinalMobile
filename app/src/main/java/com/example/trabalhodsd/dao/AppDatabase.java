package com.example.trabalhodsd.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trabalhodsd.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TarefaDAO tarefaDao();
}