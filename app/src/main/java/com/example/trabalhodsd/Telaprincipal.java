package com.example.trabalhodsd;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Telaprincipal extends AppCompatActivity {

    CalendarView mCalendar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView mUserNameText;
    private ImageButton mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal2);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mUserNameText = findViewById(R.id.textViewNomeUser);
        mUserNameText.setText(currentUser.getDisplayName());
        mLogoutButton = findViewById(R.id.bt_logout);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Intent t = new Intent(Telaprincipal.this, MainActivity.class);
                finish();
                startActivity(t);

            }
        });
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
