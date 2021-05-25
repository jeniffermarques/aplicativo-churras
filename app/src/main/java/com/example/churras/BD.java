package com.example.churras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

    private static String nome = "bancob.bd";
    private static int versao = 1;

    String[] sql = new String[]{
            "CREATE TABLE itens(nome TEXT PRIMARY KEY, quantidade INTEGER);",

            "INSERT INTO itens VALUES('HOMENS_CARNE KG', '0.5');",
            "INSERT INTO itens VALUES('HOMENS_FRANGO KG', '0.2');",
            "INSERT INTO itens VALUES('HOMENS_LINGUIÇA KG', '0.3');",
            "INSERT INTO itens VALUES('HOMENS_SUÍNO KG', '0.2');",
            "INSERT INTO itens VALUES('HOMENS_CERVEJA(LATA)', '3');",
            "INSERT INTO itens VALUES('MULHERES_CARNE KG', '0.4');",
            "INSERT INTO itens VALUES('MULHERES_FRANGO KG', '0.1');",
            "INSERT INTO itens VALUES('MULHERES_LINGUIÇA KG', '0.3');",
            "INSERT INTO itens VALUES('MULHERES_SUÍNO KG', '0.1');",
            "INSERT INTO itens VALUES('MULHERES_CERVEJA(LATA)', '1');",
            "INSERT INTO itens VALUES('MULHERES_REFRI(LATA)', '1');",
            "INSERT INTO itens VALUES('CRIANÇAS_CARNE KG', '0.2');",
            "INSERT INTO itens VALUES('CRIANÇAS_FRANGO KG', '0.1');",
            "INSERT INTO itens VALUES('CRIANÇAS_LINGUIÇA KG', '0.1');",
            "INSERT INTO itens VALUES('CRIANÇAS_SUINO KG', '0.1');",
            "INSERT INTO itens VALUES('CRIANÇAS_REFRI(LATA)', '2');"



    };

    public BD(Context context) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < sql.length; i++) {
            db.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        versao++;
        db.execSQL("DROP TABLE IF EXISTS itens");
        onCreate(db);
    }

    //crudyyyyyyyyyy

    public long inserir(String nome, String quantidade) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("quantidade", quantidade);


        return db.insert("itens", null, values);
    }


    public long atualizar(String nome, String quantidade) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quantidade", quantidade);

        return db.update("itens", values, "nome=?", new String[]{String.valueOf(nome)});
    }

    public long apagar(String nome) {
        SQLiteDatabase db = getReadableDatabase();
        return db.delete("itens", "nome=?", new String[]{String.valueOf(nome)});
    }




    public Cursor selecionar_all(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM itens",null);
    }


    public Cursor selecao(String nome){
        SQLiteDatabase db = getReadableDatabase();
        //return db.query("itens",null,null,null,null,null,null);

        return db.rawQuery("SELECT * FROM itens WHERE nome=?",new String[]{nome});

    }

    /*

    public Cursor pesquisar (String nome){
        SQLiteDatabase db = getReadableDatabase();
        //return db.query("itens",null,null,null,null,null,null);

        return db.query("itens", new String[]{ "quantidade"}, "nome" + "= ?", new String[]{(nome)},null,null,null,null);

    }

     */

    /*


    public Produto pesquisar(String nome) {
        Produto p;

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT quantidade FROM itens WHERE nome = ?", new String[] {nome});
        c.moveToFirst();

        if(c.getCount() > 0){
            p = new Produto();
            p.setQuantidade(c.getDouble(1));

        } else {
            p = null;
        }

        c.close();
        db.close();

        return p;

     */

    public Produto pesquisar (String nome){

        Produto p = new Produto();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query("itens", new String[]{ "nome","quantidade"}, "nome" + "= ?", new String[]{(nome)},null,null,null,null);

        if(cursor !=null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                p.setNome(cursor.getString(0));
                p.setQuantidade(cursor.getString(1));
            } else {
                return null;
            }
        }
        db.close();
        return p;
        }


    }














