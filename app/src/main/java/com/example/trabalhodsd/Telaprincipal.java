package com.example.trabalhodsd;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Telaprincipal extends AppCompatActivity {

    CalendarView mCalendar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        setContentView(R.layout.activity_telaprincipal2);
        mCalendar = findViewById(R.id.calendarView);
        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int ano, int mes, int dia) {
                Toast.makeText(Telaprincipal.this, "Data selecionada: " + dia+"/"+ (mes+1) +"/"+ ano, Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, "Bem vindo " + currentUser.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
    }


}
