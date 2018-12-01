/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.entidade;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author erick
 */
public class Mensagem implements Serializable {
    private Participante participante;
    private Map<String, String> informacao;

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Map<String, String> getInformacao() {
        return informacao;
    }

    public void setInformacao(Map<String, String> informacao) {
        this.informacao = informacao;
    }
}
