package com.example.quizbandeiras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome;
    private Button btnSair, btnComeçarJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnSair = findViewById(R.id.btnSair);
        btnComeçarJogo = findViewById(R.id.btnComeçarJogo);
        edtNome = findViewById(R.id.edtNome);

        edtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Verifica se o texto no EditText não está vazio
                if (s.toString().trim().length() > 0) {
                    // Habilita o botão
                    btnComeçarJogo.setEnabled(true);
                } else {
                    // Desabilita o botão
                    btnComeçarJogo.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método finish() para encerrar a atividade atual
                finish();
            }
        });

        btnComeçarJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crie uma Intent para iniciar a nova atividade
                String nomeDoUsuario = edtNome.getText().toString();
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("nomeUsuario", nomeDoUsuario);
                startActivity(intent);
            }
        });
    }
}