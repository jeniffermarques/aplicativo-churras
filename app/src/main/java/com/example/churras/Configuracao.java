package com.example.churras;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Configuracao extends AppCompatActivity {


    ListView listproduto;
    List<Produto> listaproduto;
    BD db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);


        listproduto = (ListView) findViewById(R.id.listproduto);

        db = new BD(this);
        listaproduto = new ArrayList<>();

        listarprodutos();

        listproduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Configuracao.this, alteracao.class);
                i.putExtra("nome", listaproduto.get(position).getNome());
                startActivityForResult(i, 1);

            }
        });


    }


    private void listarprodutos() {
        listaproduto.clear();
        Cursor c = db.selecionar_all();
        c.moveToFirst();
        if (c.getCount() > 0) {
            do {
                String nome = c.getString(c.getColumnIndex("nome"));
                String quantidade = c.getString(c.getColumnIndex("quantidade"));

                listaproduto.add(new Produto(nome, quantidade));
            } while (c.moveToNext());
        }
        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaproduto);
        listproduto.setAdapter(adapter);

    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            listarprodutos();
        }


    }

}












