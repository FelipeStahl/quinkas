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
    private IpAndPorta ipAndPorta;
    private Integer pontos;

    public IpAndPorta getIpAndPorta() {
        return ipAndPorta;
    }

    public void setIpAndPorta(IpAndPorta ipAndPorta) {
        this.ipAndPorta = ipAndPorta;
    }
   
    public Participante() {
        pontos = 0;
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
