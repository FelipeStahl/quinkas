/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.entidade.Pergunta;

/**
 *
 * @author Felipe-Sistema
 */
public class ManterPergunta {
    private static Pergunta perguntaAtual;

    public static Pergunta getPerguntaAtual() {
        return perguntaAtual;
    }

    public static void setPerguntaAtual(Pergunta perguntaAtual) {
        ManterPergunta.perguntaAtual = perguntaAtual;
    }

    
}
