package com.example.trabalhodsd;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.trabalhodsd.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomCalendar extends LinearLayout {
    private ImageButton back,next;
    private TextView dataAtual;
    private GridView gridCalendar;
    private static final int MAX_CALENDAR_DAYS = 42;
    private Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);
    private SimpleDateFormat mesFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);

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
                calendar.add(Calendar.MONTH,-1);
                setUpCalendario();
            }
        });

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                setUpCalendario();
            }
        });

        gridCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                //Aqui mostra a lista de atividades do dia quando clicado na data
                //View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,null);


            }
        });
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeLayout(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.calendar_view, this);
        next =  v.findViewById(R.id.bt_next);
        back =  v.findViewById(R.id.bt_back);
        dataAtual = v.findViewById(R.id.data_atual);
        gridCalendar = v.findViewById(R.id.grid_view);
        setUpCalendario();
    }

    private void setUpCalendario(){
        String currentDate = dateFormat.format(calendar.getTime());
        dataAtual.setText(currentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int firstDay = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDay);

        while(dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        myGridAdapter = new GridAdapter(context,dates,calendar,events);
        gridCalendar.setAdapter(myGridAdapter);
    }
}
