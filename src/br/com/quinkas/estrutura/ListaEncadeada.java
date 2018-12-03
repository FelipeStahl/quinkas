/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.estrutura;

import java.io.Serializable;

/**
 *
 * @author Felipe-Sistema
 */
public class ListaEncadeada implements Serializable{
  
    private No inicio;
    private Integer tamanho;

    public Object getProximo() {
        if (inicio != null) {
            Object auxiliar = inicio.getElemento();
            remover(inicio.getElemento());
            return auxiliar;
        }else{
            return null;
        }
    }

    public ListaEncadeada() {
        this.tamanho = 0;
    }

    public void adicionar(Object objeto) {
        No novoElemento = new No();
        novoElemento.setElemento(objeto);
        if (null == inicio) {
            inicio = novoElemento;
            tamanho++;
        } else {
            No auxiliar = inicio;
            while (auxiliar.getProximo() != null) {
                auxiliar = auxiliar.getProximo();
            }
            auxiliar.setProximo(novoElemento);
            tamanho++;
        }
    }

    public void adicionar(Object objeto, Integer posicao) {
        No novoElemento = new No();
        novoElemento.setElemento(objeto);

        if (posicao > tamanho) {
            throw new UnsupportedOperationException("Posição inexistente.");
        }

        if (posicao.equals(1)) {
            No auxiliar = inicio;
            inicio = novoElemento;
            inicio.setProximo(auxiliar);
            tamanho++;
        } else {
            No auxiliar = inicio;
            Integer indice = 1;
            while (!posicao.equals(indice + 1)) {
                auxiliar = auxiliar.getProximo();
                indice++;
            }
            No temporario = auxiliar.getProximo();
            novoElemento.setProximo(temporario);
            auxiliar.setProximo(novoElemento);
            tamanho++;
        }
    }

    public Object buscar(Object objeto) {
        if (inicio == null) {
            return null;
        }
        No auxiliar = inicio;
        if (objeto.equals(auxiliar.getElemento())) {
            return auxiliar.getElemento();
        } else {
            while (auxiliar.getProximo() != null) {
                auxiliar = auxiliar.getProximo();
                if (objeto.equals(auxiliar.getElemento())) {
                    return auxiliar.getElemento();
                }
            }
        }
        return null;
    }

    public Object buscar(Integer posicao) {
        if (posicao > tamanho || posicao <= 0 || tamanho <= 0) {
            return null;
        }
        No auxiliar = inicio;
        Integer indice = 1;
        while (!indice.equals(posicao)) {
            auxiliar = auxiliar.getProximo();
            indice++;
        }
        return auxiliar.getElemento();
    }

    public Boolean remover(Object objeto) {
        if (inicio == null) {
            return false;
        }
        Integer posicao = 1;
        No auxiliar = inicio;
        while (posicao <= tamanho) {
            if (auxiliar.getElemento().equals(objeto)) {
                remover(posicao);
                posicao = tamanho;
            }
            auxiliar = auxiliar.getProximo();
            posicao++;
        }
        return true;
    }

    public Boolean remover(Integer posicao) {
        if (posicao > tamanho || posicao <= 0 || tamanho <= 0) {
            return false;
        }

        if (posicao.equals(1)) {
            inicio = inicio.getProximo();
            tamanho--;
        } else {
            No anterior = null;
            No auxiliar = inicio;
            Integer indice = 1;
            while (!posicao.equals(indice)) {
                anterior = auxiliar;
                auxiliar = auxiliar.getProximo();
                indice++;
            }
            anterior.setProximo(auxiliar.getProximo());
            tamanho--;
        }
        return true;
    }

    public Integer tamanho() {
        return tamanho;
    }

    public Boolean existe(Object objeto) {
        if (inicio == null) {
            return false;
        }
        No auxiliar = inicio;
        if (objeto.equals(auxiliar.getElemento())) {
            return true;
        } else {
            while (auxiliar.getProximo() != null) {
                auxiliar = auxiliar.getProximo();
                if (objeto.equals(auxiliar.getElemento())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void limpar() {
        inicio = null;
        tamanho = 0;
    }

    @Override
    public String toString() {
        String texto = "";
        if (this.tamanho > 0) {
            No auxiliar = inicio;
            while (auxiliar != null) {
                texto += auxiliar.getElemento().toString() + ",";
                auxiliar = auxiliar.getProximo();
            }
            if (texto.length() > 0) {
                texto = texto.substring(0, texto.length() - 1);
            }
        }
        return texto;
    }  
}
