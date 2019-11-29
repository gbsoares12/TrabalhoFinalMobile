package com.example.trabalhodsd;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.trabalhodsd.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class AdapterList extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    List<Tarefa> listItems=new ArrayList<Tarefa>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<Tarefa> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;
    Context c;
    int v;
    List<Tarefa> tarefas;

    public AdapterList(Context c, int v, List<Tarefa> listItems) {
        this.c =c;
        this.listItems = listItems;
        this.v = v;
        this.tarefas = listItems;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.item_clicked);
        adapter = new ArrayAdapter<Tarefa>(c,v ,tarefas);
        setListAdapter(adapter);
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v, Tarefa tarefa) {
        adapter.add(tarefa);
        adapter.notifyDataSetChanged();
    }
}
