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
public class Participante implements Serializable, Comparable {

    private String nick;
    private IpAndPorta ipAndPorta;
    private Integer pontos;
    private Integer acerto;
    private Integer posicao;

    public Participante() {
        pontos = 0;
    }
    
    public Integer getAcerto() {
        return acerto;
    }

    public void setAcerto(Integer acerto) {
        this.acerto = acerto;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }
    

    public IpAndPorta getIpAndPorta() {
        return ipAndPorta;
    }

    public void setIpAndPorta(IpAndPorta ipAndPorta) {
        this.ipAndPorta = ipAndPorta;
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
    
    @Override
    public int compareTo(Object o) {
        return this.pontos.compareTo(((Participante) o).getPontos());
    }
}
