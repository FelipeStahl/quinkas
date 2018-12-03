/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.conexao;

import br.com.quinkas.entidade.Participante;
import br.com.quinkas.manter.ManterIp;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Alunos
 */
public class EnviaSocket {
    public static void enviarObjeto(Object objeto){
        Socket cliente;
            try {
                cliente = new Socket(ManterIp.getIpServidor().getIp(), Integer.parseInt(ManterIp.getIpServidor().getPorta()));
                ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
                oos.writeObject(objeto);
                cliente.close();
                oos.close();
            } catch (Exception e) {
                throw new UnsupportedOperationException("Erro ao enviar objeto. " + e.getMessage());
            }
    }
    
    public static void enviarObjeto(Object objeto, Participante participante){
        Socket cliente;
            try {
                cliente = new Socket(participante.getIpAndPorta().getIp(), Integer.parseInt(participante.getIpAndPorta().getPorta()));
                ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
                oos.writeObject(objeto);
                cliente.close();
                oos.close();
            } catch (Exception e) {
                throw new UnsupportedOperationException("Erro ao enviar objeto. " + e.getMessage());
            }
    }
}
