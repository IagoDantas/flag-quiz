package com.example.quizbandeiras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);
        getSupportActionBar().setTitle(R.string.rank_name);
        ImageView imgResposta = (ImageView)findViewById(R.id.imgResposta);
        TextView resposta = (TextView)findViewById(R.id.lblResposta);
        TextView nome = (TextView)findViewById(R.id.lblNome);
        Button btnJogarNovamente = (Button)findViewById(R.id.btnJogarNovamente);
        Button btnTelaPrincipal = (Button)findViewById(R.id.btnTelaPrincipal);
        Intent intent = getIntent();
        String nomeUsuario = intent.getStringExtra("nomeUsuario");
        nome.setText(nomeUsuario);
        int pontos = intent.getIntExtra("pontos", 0);

        if(intent.hasExtra("acertou")) {
            btnJogarNovamente.setVisibility(View.INVISIBLE);
            btnTelaPrincipal.setVisibility(View.INVISIBLE);
            boolean acertou = intent.getBooleanExtra("acertou", false);
            nome.setText("Nome: " + nomeUsuario);
            if (acertou) {
                imgResposta.setImageResource(R.drawable.acertou);
                resposta.setText("Acertou! Pontos: " + pontos);
                nome.setText("Nome: " + nomeUsuario);

            } else {
                imgResposta.setImageResource(R.drawable.errou);
                resposta.setText("Errou! Pontos: " + pontos);
                nome.setText("Nome: " + nomeUsuario);
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            thread.start();
        }
        else{
            btnJogarNovamente.setVisibility(View.VISIBLE);
            btnTelaPrincipal.setVisibility(View.VISIBLE);
            nome.setText("Nome: " + nomeUsuario);
            resposta.setText("Fez " + pontos + " pontos!");
            if(pontos >= 3)
                imgResposta.setImageResource(R.drawable.happy);
            else
                imgResposta.setImageResource(R.drawable.sad);
        }
    }
    public void btnJogarNovamenteOnClick(View v){
        String nomeUsuario = getIntent().getStringExtra("nomeUsuario");

        // Cria um novo Intent para iniciar a QuizActivity
        Intent intent = new Intent(this, QuizActivity.class);

        // Passa o valor do nomeUsuario para a QuizActivity
        intent.putExtra("nomeUsuario", nomeUsuario);

        // Inicia a QuizActivity com o novo Intent
        startActivity(intent);

        // Fecha a atividade atual
        finish();
    }
    public void btnTelaPrincipalOnClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}