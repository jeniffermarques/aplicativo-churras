package com.example.churras;

public class Produto {
    String nome;
    String quantidade;


    public Produto() {
        nome = "";
        quantidade = "";


    }

    public Produto(String nome, String quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }



    @Override
    public String toString(){
        return  "Item: "+ nome + " / Qtd " + quantidade;
    }



}
