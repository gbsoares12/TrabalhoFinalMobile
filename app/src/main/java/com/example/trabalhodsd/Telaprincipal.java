package com.example.trabalhodsd;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.trabalhodsd.dao.AppDatabase;
import com.example.trabalhodsd.model.RequestTarefa;
import com.example.trabalhodsd.model.Tarefa;
import com.example.trabalhodsd.retrofit.RetrofitInitializer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Telaprincipal extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView textViewBemVindo;
    private ImageButton mLogoutButton;
    private Button mImportButton;
    private List<Tarefa> listaTarefasSQLITE;
    private Button mClearButton;
    private EditText mLink;
    private CustomCalendar customCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal2);
        customCalendar  = findViewById(R.id.custom_calendar_view);

//      PEGA AS TAREFAS QUE JÁ ESTÃO SALVAS NO BANCO DE DADOS LOCAL
        listaTarefasSQLITE = carregarTarefas();

        mLink = findViewById(R.id.et_urlCalendario);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        textViewBemVindo = findViewById(R.id.textViewBemVindo);
        textViewBemVindo.setText("Bem Vindo " +currentUser.getDisplayName());
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

        mImportButton = findViewById(R.id.bt_import);
        mImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlCalendario = mLink.getText().toString();

                Call<List<Tarefa>> call = new RetrofitInitializer().getTarefas().postJson(
                        new RequestTarefa(urlCalendario));

                call.enqueue(new Callback<List<Tarefa>>() {
                    @Override
                    public void onResponse(Call<List<Tarefa>> call, Response<List<Tarefa>> response) {

                        // SE A REQUISIÇÃO FOR BEM SUCEDIDA SALVA AS TAREFAS NO SQLITE
                        for (int i = 0; i < response.body().size(); i++) {
                            registrarTarefas(response.body().get(i));
                        }
                        Intent t = new Intent(Telaprincipal.this, CustomCalendar.class);
                        finish();
                        startActivity(t);
                    }

                    @Override
                    public void onFailure(Call<List<Tarefa>> call, Throwable t) {
                        Toast.makeText(Telaprincipal.this, "Erro ao carregar as tarefas do calendário", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        for (int i = 0; i < listaTarefasSQLITE.size(); i++) {
            Log.d("TESTE", "\n\n @@@@@@@@@@@@@@@@@@ TAREFAS SALVAS NO SQLITE: " + listaTarefasSQLITE.get(i).toString() + "\n\n");
        }
    }


    void registrarTarefas(Tarefa tarefa) {
        AppDatabase db = Room.databaseBuilder(this,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        db.tarefaDao().insertAll(tarefa);
    }

    public List<Tarefa> carregarTarefas() {
        AppDatabase db = Room.databaseBuilder(this,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        return db.tarefaDao().getAll();
    }
}
