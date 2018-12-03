/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.estrutura.ListaEncadeada;

/**
 *
 * @author Felipe-Sistema
 */
public class ManterLista {
    private static ListaEncadeada lista;

    public static ListaEncadeada getLista() {
        return lista;
    }

    public static void setLista(ListaEncadeada lista) {
        ManterLista.lista = lista;
    }
    
}
