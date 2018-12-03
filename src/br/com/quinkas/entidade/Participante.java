/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.entidade;

import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author erick
 */
public class Participante implements Serializable {

    private String nick;
    private IpAndPorta ipParticipante;
    private Integer pontos;

    public Participante() {
        pontos = 0;
    }

    public IpAndPorta getIpParticipante() {
        return ipParticipante;
    }

    public void setIpParticipante(IpAndPorta ipParticipante) {
        this.ipParticipante = ipParticipante;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
