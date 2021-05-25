package com.example.churras;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import static android.widget.Toast.LENGTH_LONG;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {


    private static final String EditText = null;
    EditText homens, mulheres, criancas, ltcerveja, ltrefri, kgcarne, kgfrango, kglinguica, kgporco, carvao, gelo;
    EditText resultado = null;
    CheckBox criancapaga;
    Button btcalcular, chip2, irconfig;
    DecimalFormat currency = new DecimalFormat("$###,###.##");
    BD db;

    // Chamada quando a activity é criada pela primeira vez

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Associa um arquivo de layout a esta classe

        setContentView(R.layout.activity_main);
        criancapaga = (CheckBox) findViewById(R.id.criancapaga);
        homens = (EditText) findViewById(R.id.homens);
        mulheres = (EditText) findViewById(R.id.mulheres);
        criancas = (EditText) findViewById(R.id.criancas);
        ltcerveja = (EditText) findViewById(R.id.ltcerveja);
        ltrefri = (EditText) findViewById(R.id.ltrefri);
        kgcarne = (EditText) findViewById(R.id.kgcarne);
        kgfrango = (EditText) findViewById(R.id.kgfrango);
        kglinguica = (EditText) findViewById(R.id.kglinguica);
        kgporco = (EditText) findViewById(R.id.kgporco);
        carvao = (EditText) findViewById(R.id.carvao);
        gelo = (EditText) findViewById(R.id.gelo);
        Button btcalcular = findViewById(R.id.btcalcular);
        Button chip2 = findViewById(R.id.chip2);
        Button irconfig = findViewById(R.id.irconfig);

        db = new BD(this);




        //      TextView resultado = findViewById(R.id.resultado);
        DecimalFormat df = new DecimalFormat("0.00");

        Toast.makeText(getApplicationContext(), "Digite A Largura", LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this, "Digite A Largura", Toast.LENGTH_LONG).show();



        irconfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*


                db.inserir("Carne_Homem","0.5");
                db.inserir("Frango_Homem","0.2");
                db.inserir("Linguiça_Homem","0.3");
                db.inserir("Suíno_Homem","0.2");
                db.inserir("Cerveja_Homem","3");
                db.inserir("CarneM","0.4");
                db.inserir("FrangoM","0.1");
                db.inserir("LinguiçaM","0.3");
                db.inserir("SuínoM","0.1");
                db.inserir("CervejaM","1");
                db.inserir("RefrigeranteM","1");

                 */

                Intent i = new Intent(MainActivity.this, Configuracao.class);
                startActivity(i);
            }
        });

        chip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                dialogo.setTitle("Desenvolvedores:");
                dialogo.setMessage("5812038 Allysson C.S. Ferreira" + "\n" + "3932443 Daniele M. Lima" + "\n" + "2199170 Guilherme A.F. Silva" + "\n" + "3846413 Jeniffer M. Silva" + "\n" + "8426980 Kaique M. Souza" + "\n" + "6602160 Marcio Ribeiro Oliveira" + "\n" + "5693927 Vilmara C. Ramos" + "\n" + "1588959 Wagner A. Mathias" + "\n" + "\n" + "'Computação para Dispositivos Móveis'" + "\n" + "Nosso objetivo foi desenvolver uma aplicação simples mas útil, visando solucionar um problema comum nas interações sociais, dividir os custos de um evento entre amigos de maneira clara e justa. Esperamos que gostem." + "\n" + "\n" + "Análise e Desenvolvimento de Sistemas" + "\n" + "FMU - Formandos 12/2021" + "\n" + "\n" + "Gratidão!");
                dialogo.setNeutralButton("OK", null);
                dialogo.show();
            }
        });




        btcalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // int e double variaveis para se lembrar... na calculadora coloquei int
/*
                if(homens.equals(null)){
                    Toast.makeText(getApplicationContext(), "Digite A Largura", Toast.LENGTH_LONG).show();
                    return ;
                }
                else if(mulheres.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Digite A Largura", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(criancas.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Digite O Peso", Toast.LENGTH_SHORT).show();
                    return;
                }
   */


                int num1 = parseInt(homens.getText().toString());
                int num2 = parseInt(mulheres.getText().toString());
                int num3 = parseInt(criancas.getText().toString());

                int soma = num1 + num2 + num3;

                //carvao - a cada 10 pessoas aumenta 1 saco, limitado a 5 sacos com mais de 50 pessoas
                double qcarvao = Double.parseDouble(carvao.getText().toString());
                if (soma <= 10) {
                    qcarvao = 1;
                } else if (soma > 10 && soma <= 20) {
                    qcarvao = 2;
                } else if (soma > 20 && soma <= 30) {
                    qcarvao = 3;
                } else if (soma > 30 && soma <= 40) {
                    qcarvao = 4;
                } else {
                    qcarvao = 5;
                }
                //Daniiii rssss
                // o valor do carvao e gelo será dividido entre homens e mulheres.... mas tera q ter um if se nao tiver um ou outro (homem = null ou mulher = null
                double vlcarvao = Double.parseDouble(carvao.getText().toString());
                double $carvao = vlcarvao * qcarvao;


                //gelo - a cada 10 pessoas aumenta 1 saco, limitado a 5 sacos com mais de 50 pessoas
                double qgelo = Double.parseDouble(gelo.getText().toString());

                if (soma <= 10) {
                    qgelo = 1;
                } else if (soma > 10 && soma <= 20) {
                    qgelo = 2;
                } else if (soma > 20 && soma <= 30) {
                    qgelo = 3;
                } else if (soma > 30 && soma <= 40) {
                    qgelo = 4;
                } else {
                    qgelo = 5;
                }
                double vlgelo = Double.parseDouble(gelo.getText().toString());
                double $gelo = vlgelo * qgelo;


                //homens
                double kcarneh = Double.parseDouble(kgcarne.getText().toString());
                double kfrangoh = Double.parseDouble(kgfrango.getText().toString());
                double klinguicah = Double.parseDouble(kglinguica.getText().toString());
                double kporcoh = Double.parseDouble(kgporco.getText().toString());
                double lcervejah = Double.parseDouble(ltcerveja.getText().toString());
                //Produto it1 = db.pesquisar("HOMENS_CARNE KG");
                //Double.parseDouble(String.valueOf(it1));
                double carneh = num1 * 0.5; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlcarneh = carneh * kcarneh;
                double frangoh = num1 * 0.3; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlfrangoh = frangoh * kfrangoh;
                double linguicah = num1 * 0.35; // este valor é o kg por pessoa e deve ir para uma tabela
                double vllinguicah = linguicah * klinguicah;
                double porcoh = num1 * 0.25; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlporcoh = porcoh * kporcoh;
                int cervejah = num1 * 5; // este valor é a quantidade por pessoa e deve ir para uma tabela
                double vlcervejah = cervejah * lcervejah;
                double toth = ((vlcarneh + vlcervejah + vlfrangoh + vllinguicah + vlporcoh) / num1) + (($carvao / 2) / num1) + (($gelo / 2) / num1);
                double tothgc = ((vlcarneh + vlcervejah + vlfrangoh + vllinguicah + vlporcoh + $carvao + $gelo) / num1);

                //mulheres
                double kcarnem = Double.parseDouble(kgcarne.getText().toString());
                double kfrangom = Double.parseDouble(kgfrango.getText().toString());
                double klinguicam = Double.parseDouble(kglinguica.getText().toString());
                double kporcom = Double.parseDouble(kgporco.getText().toString());
                double lcervejam = Double.parseDouble(ltcerveja.getText().toString());
                double lrefrim = Double.parseDouble(ltrefri.getText().toString());
                double carnem = num2 * 0.3; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlcarnem = carnem * kcarnem;
                double frangom = num2 * 0.2; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlfrangom = frangom * kfrangom;
                double linguicam = num2 * 0.2; // este valor é o kg por pessoa e deve ir para uma tabela
                double vllinguicam = linguicam * klinguicam;
                double porcom = num2 * 0.15; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlporcom = porcom * kporcom;
                int cervejam = num2 * 2; // este valor é a quantidade por pessoa e deve ir para uma tabela
                double vlcervejam = cervejam * lcervejam;
                int refrigerantem = num2 * 1; // este valor é a quantidade por pessoa e deve ir para uma tabela
                double vlrefrim = refrigerantem * lrefrim;
                double totm = ((vlcarnem + vlcervejam + vlfrangom + vlrefrim + vllinguicam + vlporcom) / num2) + (($carvao / 2) / num2) + (($gelo / 2) / num2);
                double totmgc = ((vlcarnem + vlcervejam + vlfrangom + vlrefrim + vllinguicam + vlporcom + $carvao + $gelo) / num2);


                //crianças
                double kcarnec = Double.parseDouble(kgcarne.getText().toString());
                double kfrangoc = Double.parseDouble(kgfrango.getText().toString());
                double klinguicac = Double.parseDouble(kglinguica.getText().toString());
                double kporcoc = Double.parseDouble(kgporco.getText().toString());
                double lrefric = Double.parseDouble(ltrefri.getText().toString());
                double carnec = num3 * 0.2; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlcarnec = carnec * kcarnec;
                double frangoc = num3 * 0.1; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlfrangoc = frangoc * kfrangoc;
                double linguicac = num3 * 0.15; // este valor é o kg por pessoa e deve ir para uma tabela
                double vllinguicac = linguicac * klinguicac;
                double porcoc = num3 * 0.1; // este valor é o kg por pessoa e deve ir para uma tabela
                double vlporcoc = porcoc * kporcoc;
                int refrigerantec = num3 * 2; // este valor é a quantidade por pessoa e deve ir para uma tabela
                double vlrefric = refrigerantec * lrefric;
                double totc = (vlcarnec + vlfrangoc + vlrefric + vllinguicac + vlporcoc) / num3;


                if (criancapaga.isChecked()) {

                    // RESULTADOS DIFERENTES PARA O CASO DE NAO HAVER HOMENS, MULHERES OU CRIANÇAS NO CALCULO

                    //num1 = quantidade de homens 0, mulheres e crianças apenas
                    if (num1 == 0 && num2 >= 1 && num3 >= 1) {
                        //quantidade sem homens
                        double carne = carnec + carnem;
                        double frango = frangoc + frangom;
                        double linguica = linguicac + linguicam;
                        double porco = porcoc + porcom;
                        int cerveja = cervejam;
                        int refrigerante = refrigerantec + refrigerantem;

                        //valores sem homem
                        double $cerveja = vlcervejam;
                        double $refrigerante = vlrefric + vlrefrim;
                        double $carne = vlcarnem + vlcarnec;
                        double $frango = vlfrangoc + vlfrangom;
                        double $porco = vlporcoc + vlporcom;
                        double $linguica = vllinguicac + vllinguicam;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;


                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados ♥ R$" + df.format($total) + "\n" + "DIA DAS MAMÃES");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☻☺ R$" + df.format(totmgc) + " p/ Mulher ♀" + "\n" + "☺☻ R$" + df.format(totc) + " p/ Criança, mamãe paga!!!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();

                        //num2 = quantidade de mulheres 0, homens e crianças apenas
                    } else if (num1 >= 1 && num2 == 0 && num3 >= 1) {
                        //quantidade sem mulher
                        double carne = carnec + carneh;
                        double frango = frangoc + frangoh;
                        double linguica = linguicac + linguicah;
                        double porco = porcoc + porcoh;
                        int cerveja = cervejah;
                        int refrigerante = refrigerantec;

                        //valores sem mulheres
                        double $cerveja = vlcervejah;
                        double $refrigerante = vlrefric;
                        double $carne = vlcarneh + vlcarnec;
                        double $frango = vlfrangoc + vlfrangoh;
                        double $porco = vlporcoc + vlporcoh;
                        double $linguica = vllinguicac + vllinguicah;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados" + "\n" + "Dia dos Papais ♥ R$" + df.format($total));
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(tothgc) + " p/ Homem ♂" + "\n" + "☺☻ R$" + df.format(totc) + " p/ Criança, papai paga!!!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //num3 = quantidade de crianças 0, apenas adultos
                    } else if (num1 >= 1 && num2 >= 1 && num3 == 0) {
                        //quantidade sem crianças
                        double carne = carneh + carnem;
                        double frango = frangoh + frangom;
                        double linguica = linguicah + linguicam;
                        double porco = porcoh + porcom;
                        int cerveja = cervejam + cervejah;
                        int refrigerante = refrigerantem;

                        //valores sem crianças
                        double $cerveja = vlcervejah + vlcervejam;
                        double $refrigerante = vlrefrim;
                        double $carne = vlcarneh + vlcarnem;
                        double $frango = vlfrangoh + vlfrangom;
                        double $porco = vlporcoh + vlporcom;
                        double $linguica = vllinguicah + vllinguicam;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados ♥ R$" + df.format($total) + "\n" + "Churras da Galera");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(toth) + " p/ Homem ♂" + "\n" + "☻☺ R$" + df.format(totm) + " p/ Mulher ♀");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //só os caras
                    } else if (num3 == 0 && num1 >= 1 && num2 == 0) {
                        //quantidade sem crianças
                        double carne = carneh + carnec;
                        double frango = frangoh + frangoc;
                        double linguica = linguicah + linguicac;
                        double porco = porcoh + porcoc;
                        int cerveja = cervejah;
                        int refrigerante = refrigerantec;

                        double $cerveja = vlcervejah;
                        double $refrigerante = vlrefric;
                        double $carne = vlcarneh + vlcarnec;
                        double $frango = vlfrangoh + vlfrangoc;
                        double $porco = vlporcoh + vlporcoc;
                        double $linguica = vllinguicah + vllinguicac;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Brothers ♣ R$" + df.format($total) + "\n" + "DIA DOS CAMARADAS");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante não... pega mais breja entao!! " + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(tothgc) + " p/ cada brother ♂" + "\n" + "\n" + "Avisem as esposas!!!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //só as minas
                    } else if (num3 == 0 && num2 >= 1 && num1 == 0) {
                        //quantidade sem crianças
                        double carne = carnem + carnec;
                        double frango = frangom + frangoc;
                        double linguica = linguicam + linguicac;
                        double porco = porcom + porcoc;
                        int cerveja = cervejam;
                        int refrigerante = refrigerantec + refrigerantem;

                        double $cerveja = vlcervejam;
                        double $refrigerante = vlrefric + vlrefrim;
                        double $carne = vlcarnem + vlcarnec;
                        double $frango = vlfrangom + vlfrangoc;
                        double $porco = vlporcom + vlporcoc;
                        double $linguica = vllinguicam + vllinguicac;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Amigas ♥ R$" + df.format($total) + "\n" + "DIA DAS GAROTAS");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(totmgc) + " p/ Amiga ♀" + "\n" + "\n" + "Vamos botar o papo em dia!!!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //só crianças
                    } else if (num3 >= 1 && num2 == 0 && num1 == 0) {

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("Seus Danadinhos");
                        dialogo.setMessage("Sem a presença de um adulto não pode." + "\n" + "\n" + "Chamem seus pais, vizinho e amigos e organizem uma grande festa!!!!");
                        dialogo.setNeutralButton("Vamos tentar de novo?", null);
                        dialogo.show();


                    } else if (num3 >= 1 && num2 >= 1 && num1 >= 1) {


                        //quantidade
                        double carne = carnec + carneh + carnem;
                        double frango = frangoc + frangoh + frangom;
                        double linguica = linguicac + linguicah + linguicam;
                        double porco = porcoc + porcoh + porcom;
                        int cerveja = cervejah + cervejam;
                        int refrigerante = refrigerantec + refrigerantem;

                        //valores
                        double $cerveja = vlcervejah + vlcervejam;
                        double $refrigerante = vlrefric + vlrefrim;
                        double $carne = vlcarneh + vlcarnem + vlcarnec;
                        double $frango = vlfrangoc + vlfrangoh + vlfrangom;
                        double $porco = vlporcoc + vlporcoh + vlporcom;
                        double $linguica = vllinguicac + vllinguicah + vllinguicam;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        // int num1 = Integer.parseInt(homens.getText().toString()); double num3 = Double.parseDouble(criancas.getText().toString());
                        //int num2 = Integer.parseInt(mulheres.getText().toString());
                        //int num3 = Integer.parseInt(criancas.getText().toString());
                        //int soma = num1 + num2 + num3;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados ♥ R$" + df.format($total) + "\n" + "Pra família toda.");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(toth) + " p/ Homem ♂" + "\n" + "☻☺ R$" + df.format(totm) + " p/ Mulher ♀" + "\n" + "☺☻ R$" + df.format(totc) + " p/ Criança, papai paga!!!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();

                    }
                } else
                    // RESULTADOS DIFERENTES PARA O CASO DE NAO HAVER HOMENS, MULHERES OU CRIANÇAS NO CALCULO

                    //num1 = quantidade de homens 0, mulheres e crianças apenas
                    if (num1 == 0 && num2 >= 1 && num3 >= 1) {
                        //quantidade sem homens
                        double carne = carnec + carnem;
                        double frango = frangoc + frangom;
                        double linguica = linguicac + linguicam;
                        double porco = porcoc + porcom;
                        int cerveja = cervejam;
                        int refrigerante = refrigerantec + refrigerantem;

                        //valores sem homem
                        double $cerveja = vlcervejam;
                        double $refrigerante = vlrefric + vlrefrim;
                        double $carne = vlcarnem + vlcarnec;
                        double $frango = vlfrangoc + vlfrangom;
                        double $porco = vlporcoc + vlporcom;
                        double $linguica = vllinguicac + vllinguicam;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;


                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados ♥ R$" + df.format($total) + "\n" + "DIA DAS MAMÃES");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☻☺ R$" + df.format(totmgc + totc) + " p/ Mulher ♀");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();

                        //num2 = quantidade de mulheres 0, homens e crianças apenas
                    } else if (num1 >= 1 && num2 == 0 && num3 >= 1) {
                        //quantidade sem mulher
                        double carne = carnec + carneh;
                        double frango = frangoc + frangoh;
                        double linguica = linguicac + linguicah;
                        double porco = porcoc + porcoh;
                        int cerveja = cervejah;
                        int refrigerante = refrigerantec;

                        //valores sem mulheres
                        double $cerveja = vlcervejah;
                        double $refrigerante = vlrefric;
                        double $carne = vlcarneh + vlcarnec;
                        double $frango = vlfrangoc + vlfrangoh;
                        double $porco = vlporcoc + vlporcoh;
                        double $linguica = vllinguicac + vllinguicah;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados" + "\n" + "Dia dos Papais ♥ R$" + df.format($total));
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(tothgc + totc) + " p/ Homem ♂");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //num3 = quantidade de crianças 0, apenas adultos
                    } else if (num1 >= 1 && num2 >= 1 && num3 == 0) {
                        //quantidade sem crianças
                        double carne = carneh + carnem;
                        double frango = frangoh + frangom;
                        double linguica = linguicah + linguicam;
                        double porco = porcoh + porcom;
                        int cerveja = cervejam + cervejah;
                        int refrigerante = refrigerantem;

                        //valores sem crianças
                        double $cerveja = vlcervejah + vlcervejam;
                        double $refrigerante = vlrefrim;
                        double $carne = vlcarneh + vlcarnem;
                        double $frango = vlfrangoh + vlfrangom;
                        double $porco = vlporcoh + vlporcom;
                        double $linguica = vllinguicah + vllinguicam;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados ♥ R$" + df.format($total) + "\n" + "Churras da Galera");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(toth) + " p/ Homem ♂" + "\n" + "☻☺ R$" + df.format(totm) + " p/ Mulher ♀");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //só os caras
                    } else if (num3 == 0 && num1 >= 1 && num2 == 0) {
                        //quantidade sem crianças
                        double carne = carneh + carnec;
                        double frango = frangoh + frangoc;
                        double linguica = linguicah + linguicac;
                        double porco = porcoh + porcoc;
                        int cerveja = cervejah;
                        int refrigerante = refrigerantec;

                        double $cerveja = vlcervejah;
                        double $refrigerante = vlrefric;
                        double $carne = vlcarneh + vlcarnec;
                        double $frango = vlfrangoh + vlfrangoc;
                        double $porco = vlporcoh + vlporcoc;
                        double $linguica = vllinguicah + vllinguicac;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Brothers ♣ R$" + df.format($total) + "\n" + "DIA DOS CAMARADAS");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante não... pega mais breja entao!! " + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(tothgc) + " p/ cada brother ♂" + "\n" + "\n" + "Avisem as esposas!!!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //só as minas
                    } else if (num3 == 0 && num2 >= 1 && num1 == 0) {
                        //quantidade sem crianças
                        double carne = carnem + carnec;
                        double frango = frangom + frangoc;
                        double linguica = linguicam + linguicac;
                        double porco = porcom + porcoc;
                        int cerveja = cervejam;
                        int refrigerante = refrigerantec + refrigerantem;

                        double $cerveja = vlcervejam;
                        double $refrigerante = vlrefric + vlrefrim;
                        double $carne = vlcarnem + vlcarnec;
                        double $frango = vlfrangom + vlfrangoc;
                        double $porco = vlporcom + vlporcoc;
                        double $linguica = vllinguicam + vllinguicac;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Amigas ♥ R$" + df.format($total) + "\n" + "DIA DAS GAROTAS");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(totmgc) + " p/ Amiga ♀" + "\n" + "\n" + "Vamos botar o papo em dia!!!");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();


                        //só crianças
                    } else if (num3 >= 1 && num2 == 0 && num1 == 0) {

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("Seus Danadinhos");
                        dialogo.setMessage("Sem a presença de um adulto não pode." + "\n" + "\n" + "Chamem seus pais, vizinho e amigos e organizem uma grande festa!!!!");
                        dialogo.setNeutralButton("Vamos tentar de novo?", null);
                        dialogo.show();


                    } else if (num3 >= 1 && num2 >= 1 && num1 >= 1) {


                        //quantidade
                        double carne = carnec + carneh + carnem;
                        double frango = frangoc + frangoh + frangom;
                        double linguica = linguicac + linguicah + linguicam;
                        double porco = porcoc + porcoh + porcom;
                        int cerveja = cervejah + cervejam;
                        int refrigerante = refrigerantec + refrigerantem;

                        //valores
                        double $cerveja = vlcervejah + vlcervejam;
                        double $refrigerante = vlrefric + vlrefrim;
                        double $carne = vlcarneh + vlcarnem + vlcarnec;
                        double $frango = vlfrangoc + vlfrangoh + vlfrangom;
                        double $porco = vlporcoc + vlporcoh + vlporcom;
                        double $linguica = vllinguicac + vllinguicah + vllinguicam;
                        double $total = $cerveja + $carne + $refrigerante + $frango + $linguica + $porco + $carvao + $gelo;

                        // int num1 = Integer.parseInt(homens.getText().toString()); double num3 = Double.parseDouble(criancas.getText().toString());
                        //int num2 = Integer.parseInt(mulheres.getText().toString());
                        //int num3 = Integer.parseInt(criancas.getText().toString());
                        //int soma = num1 + num2 + num3;

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        dialogo.setTitle("☺" + soma + " Convidados ♥ R$" + df.format($total) + "\n" + "Pra família toda.");
                        dialogo.setMessage("CÓMIS" + "\n" + "♠ Carne: " + df.format(carne) + " kg = R$" + df.format($carne) + "\n" + "♥ Frango: " + df.format(frango) + " kg = R$" + df.format($frango) + "\n" + "♣ Linguiça: " + df.format(linguica) + " kg = R$" + df.format($linguica) + "\n" + "♦ Suíno: " + df.format(porco) + " kg = R$" + df.format($porco) + "\n" + "\n" + "BÉBIS" + "\n" + "► Cerveja: " + cerveja + " latas = R$" + df.format($cerveja) + "\n" + "► Refrigerante: " + refrigerante + " latas = R$" + df.format($refrigerante) + "\n" + "\n" + "ESQUECE NÃO..." + "\n" + "► Gelo: " + qgelo + " Pct = R$" + df.format($gelo) + "\n" + "► Carvao: " + qcarvao + " Pct = R$" + df.format($carvao) + "\n" + "\n" + "VAMOS RACHAR O CHURRAS??" + "\n" + "☺☻ R$" + df.format(toth + (totc / num1)) + " p/ Homem ♂" + "\n" + "☻☺ R$" + df.format(totm + (totc / num2)) + " p/ Mulher ♀");
                        dialogo.setNeutralButton("OK", null);
                        dialogo.show();

                    }


            }
        });
    }








    /* TESTE DO CRUD*/


}