package com.example.trabalhodsd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalhodsd.model.Tarefa;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridAdapter extends ArrayAdapter {

    private List<Date> datas;
    private Calendar calendar;
    private List<Tarefa> eventos;
    private LayoutInflater layoutInflater;


    public GridAdapter(@NonNull Context context, List<Date>dates, Calendar dataAtual, List<Tarefa> eventos) {
        super(context, R.layout.single_cell);
        this.calendar = dataAtual;
        this.datas = dates;
        this.eventos = eventos;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date monthDate = datas.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = calendar.get(Calendar.MONTH)+1;
        int dispalyYear = calendar.get(Calendar.YEAR);

        View v = convertView;
        if(v == null){
            v = layoutInflater.inflate(R.layout.single_cell, parent, false);
        }
        TextView tx = v.findViewById(R.id.dia);
        tx.setText(String.valueOf(day));
        System.out.println("aquii");
        return v;
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
}
