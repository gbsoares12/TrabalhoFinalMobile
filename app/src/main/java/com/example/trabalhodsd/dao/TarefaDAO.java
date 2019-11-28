package com.example.trabalhodsd.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trabalhodsd.model.Tarefa;

import java.util.List;

@Dao
public interface TarefaDAO {
    @Query("SELECT * FROM tarefa")
    List<Tarefa> getAll();

    @Insert
    void insertAll(Tarefa... tarefas);

    @Delete
    void delete(Tarefa tarefa);
}
