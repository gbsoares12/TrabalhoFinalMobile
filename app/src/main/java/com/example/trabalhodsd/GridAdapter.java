package com.example.trabalhodsd;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalhodsd.model.Tarefa;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GridAdapter extends ArrayAdapter {

    private List<Date> datas;
    private Calendar calendar;
    private List<Tarefa> eventos;
    private LayoutInflater layoutInflater;
    private int mes;
    private Map<String, List<Tarefa>> listTarefas = new HashMap<String, List<Tarefa>>();


    public GridAdapter(@NonNull Context context, List<Date> dates, Calendar dataAtual, List<Tarefa> eventos) {
        super(context, R.layout.single_cell);
        this.calendar = dataAtual;
        this.datas = dates;
        this.eventos = eventos;
        layoutInflater = LayoutInflater.from(context);
        for (int i = 0; i < 30; i++) {
            String index;
            if (i < 10) {
                index = "0" + i;
            } else {
                index = i+"";
            }
            listTarefas.put(index, new ArrayList<Tarefa>());
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date monthDate = datas.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthDate);


        View v = convertView;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.single_cell, parent, false);
        }
        TextView tx = v.findViewById(R.id.dia);
        tx.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        Calendar evento = Calendar.getInstance();
        TextView taref = v.findViewById(R.id.eventos);
        //tamanho 10
        for (int i = 0; i < eventos.size(); i++) {
            List<Tarefa> arrayTarefasPorDia = null;
            evento.setTime(convertStrignToDate(eventos.get(i).getDataCompleta()));
            int mes = evento.get(Calendar.MONTH);
            mes += 1;
            if (Integer.parseInt(eventos.get(i).getDia()) == calendar.get(Calendar.DAY_OF_MONTH)
                    && Integer.parseInt(eventos.get(i).getMes()) == mes
                    && Integer.parseInt(eventos.get(i).getAno()) == evento.get(Calendar.YEAR)) {
                listTarefas.get(eventos.get(i).getDia()).add(eventos.get(i));
                taref.setText(listTarefas.get(eventos.get(i).getDia()).size() + "");
                evento.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        return v;
    }

    private Date convertStrignToDate(String eventoData) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date data = null;
        try {
            data = format.parse(eventoData);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return datas.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }


    public Map<String, List<Tarefa>> getListTarefas() {
        return listTarefas;
    }

    public void setListTarefas(Map<String, List<Tarefa>> listTarefas) {
        this.listTarefas = listTarefas;
    }
}
