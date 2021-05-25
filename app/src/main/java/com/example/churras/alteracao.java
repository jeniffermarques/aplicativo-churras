package com.example.churras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class alteracao extends AppCompatActivity {

    EditText modiprodutoa, modiquantia;
    Button botmodifica;
    Intent i;
    BD db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao);

        modiprodutoa = findViewById(R.id.modiproduto);
        modiquantia = findViewById(R.id.modiquanti);
        botmodifica = findViewById(R.id.botmodific);

        i = getIntent();
        db = new BD(this);
        String nome = i.getExtras().getString("nome");

        carregardados(nome);


        botmodifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long res = db.atualizar(nome, (modiquantia.getText().toString()));
                if (res > 0){
                    Toast.makeText(alteracao.this,"atualizado",Toast.LENGTH_SHORT).show();
                    setResult(1, i);
                    finish();
                } else {
                    Toast.makeText(alteracao.this,"error",Toast.LENGTH_SHORT).show();

                }


            }


        });


    }

    private void carregardados(String nome) {
        Cursor c = db.selecao(nome);
        c.moveToFirst();
        if (c.getCount() == 1) {
            double quantidade = c.getDouble(c.getColumnIndex("quantidade"));
            String qtd = String.valueOf(quantidade);
            modiprodutoa.setText(nome);
            modiquantia.setText(qtd);

        } else if (c.getCount() == 0) {
            Toast.makeText(this,"Não Existe",Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(this,"Erro",Toast.LENGTH_SHORT).show();

        }


    }

}