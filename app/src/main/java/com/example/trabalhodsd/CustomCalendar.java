package com.example.trabalhodsd;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.room.Room;

import com.example.trabalhodsd.dao.AppDatabase;
import com.example.trabalhodsd.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomCalendar extends LinearLayout {
    private ImageButton back, next;
    private TextView dataAtual;
    private GridView gridCalendar;
    private static final int MAX_CALENDAR_DAYS = 42;
    private Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private SimpleDateFormat mesFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    private SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private GridAdapter myGridAdapter;
    private AlertDialog alert;
    private List<Date> dates = new ArrayList<>();
    private List<Tarefa> events = new ArrayList<>();

    public CustomCalendar(Context context) {
        super(context);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeLayout();

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                setUpCalendario();
            }
        });

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                setUpCalendario();
            }
        });

        gridCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clicked, null);

                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                parent.getItemAtPosition(position);
                String[] arrayData = parent.getItemAtPosition(position).toString().split(" ");
                LinearLayout parentlayout = mView.findViewById(R.id.layout_click);
                if (myGridAdapter.getListTarefas().get(arrayData[2]) != null) {
                    for (int i = 0; i < myGridAdapter.getListTarefas().get(arrayData[2]).size(); i++) {
                        TextView txt = new TextView(parent.getContext());
                        txt.setText(myGridAdapter.getListTarefas().get(arrayData[2]).get(i).toString());
                        parentlayout.addView(txt);
                    }
                }
            }
        });
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeLayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.calendar_view, this);
        next = v.findViewById(R.id.bt_next);
        back = v.findViewById(R.id.bt_back);
        dataAtual = v.findViewById(R.id.data_atual);
        gridCalendar = v.findViewById(R.id.grid_view);
        setUpCalendario();
    }

    public void setUpCalendario() {
        String currentDate = dateFormat.format(calendar.getTime());
        dataAtual.setText(currentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDay = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDay);
        eventosMes(mesFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));

        while (dates.size() < MAX_CALENDAR_DAYS) {
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        myGridAdapter = new GridAdapter(context, dates, calendar, events);
        gridCalendar.setAdapter(myGridAdapter);
    }

    private void eventosMes(String mes, String ano) {
        events.clear();
        for (Tarefa f : carregarTarefas()) {
            if (Integer.parseInt(f.getData_fim().substring(5, 7)) == mesToNumero(mes)) {
                events.add(f);
            }
        }
    }

    public List<Tarefa> carregarTarefas() {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        return db.tarefaDao().getAll();
    }

    private int mesToNumero(String mes) {
        switch (mes) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        return 0;
    }

}
