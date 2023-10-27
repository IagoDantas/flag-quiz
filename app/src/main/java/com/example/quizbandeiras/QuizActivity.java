package com.example.quizbandeiras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    Intent it;
    ImageView imgQuiz;
    RadioGroup rdgQuiz;
    RadioButton rdbResposta1,rdbResposta2, rdbResposta3, rdbResposta4;
    Button btnResponder;
    int respostaCerta = R.id.rdbResposta1;
    int pontos = 0;

    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("bulgaria", R.id.rdbResposta1, "Bulgária", "Brasil", "Rússia", "China"));
            add(new Questao("brasil", R.id.rdbResposta3, "Canadá", "Estados Unidos", "Brasil", "Coreia do Norte"));
            add(new Questao("canada", R.id.rdbResposta2, "Rússia", "Canadá", "Egito", "Marrocos"));
            add(new Questao("china", R.id.rdbResposta4, "Argélia", "Níger", "Filipinas", "China"));
            add(new Questao("coreia_sul", R.id.rdbResposta2, "Filipinas", "Coreia do Sul", "Coreia do Norte", "Índia"));
            add(new Questao("cuba", R.id.rdbResposta1, "Cuba", "Venezuela", "Peru", "Argentina"));
            add(new Questao("guatemala", R.id.rdbResposta3, "Sri Lanka", "Granada", "Guatemala", "Paraguai"));
            add(new Questao("hungria", R.id.rdbResposta3, "França", "Venezuela", "Hungria", "Alemanha"));
            add(new Questao("italia", R.id.rdbResposta4, "Equador", "Suiça", "França", "Itália"));
            add(new Questao("usa", R.id.rdbResposta2, "Brasil", "Estados Unidos", "México", "Bolívia"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setTitle(R.string.app_name);

        imgQuiz = findViewById(R.id.imgQuiz);
        rdbResposta1 = findViewById(R.id.rdbResposta1);
        rdbResposta2 = findViewById(R.id.rdbResposta2);
        rdbResposta3 = findViewById(R.id.rdbResposta3);
        rdbResposta4 = findViewById(R.id.rdbResposta4);
        btnResponder = findViewById(R.id.btnResponder);
        rdgQuiz = findViewById(R.id.rdgQuiz);
        btnResponder.setEnabled(false);
        rdgQuiz.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Verifique se algum radio button está selecionado
                boolean radioSelected = checkedId != -1;

                // Habilite ou desabilite o botão de resposta com base na seleção
                btnResponder.setEnabled(radioSelected);
            }
        });
        carregarQuestao();

    }
    protected void onRestart(){
        super.onRestart();
        carregarQuestao();
    }
    @Override
    public void onBackPressed() {
        // Fecha todas as atividades associadas a esta aplicação
        finishAffinity();
    }
    private void carregarQuestao(){
        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            imgQuiz.setImageResource(q.getImageResourceId(getApplicationContext()));
            List<String> resposta = q.getRespostas();
            rdbResposta1.setText(resposta.get(0));
            rdbResposta2.setText(resposta.get(1));
            rdbResposta3.setText(resposta.get(2));
            rdbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            btnResponder.setEnabled(false);
        }
        else{
            Intent intent = new Intent(this, RespostaActivity.class);
            String nomeUsuario = getIntent().getStringExtra("nomeUsuario");
            intent.putExtra("pontos", pontos);
            intent.putExtra("nomeUsuario", nomeUsuario);
            startActivity(intent);
            finish();
        }
    }
    public void btnResponderOnClick(View v){
        int checkedRadioButtonId = rdgQuiz.getCheckedRadioButtonId();

        // Verifique se algum radio button está selecionado
        if (checkedRadioButtonId != -1) {
            RadioButton rb = findViewById(checkedRadioButtonId);
            Intent intent = new Intent(this, RespostaActivity.class);

            // Obtenha o nome do usuário do Intent original que iniciou a atividade QuizActivity
            String nomeUsuario = getIntent().getStringExtra("nomeUsuario");

            if (checkedRadioButtonId == respostaCerta) {
                intent.putExtra("acertou", true);
                pontos++;
            } else {
                intent.putExtra("acertou", false);
            }

            intent.putExtra("pontos", pontos);
            intent.putExtra("nomeUsuario", nomeUsuario); // Passe o nome do usuário para o novo Intent
            startActivity(intent);
            rb.setChecked(false);
        }
    }
}