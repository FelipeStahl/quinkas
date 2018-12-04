/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.conexao.ISocket;
import br.com.quinkas.conexao.Server;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class ManterServer {

    
    private static ServerSocket servidor;
    private static Socket cliente;
    private static ObjectInputStream ois;
    private static ISocket painelAtual;

    public static ISocket getPainelAtual() {
        return painelAtual;
    }

    public static void setPainelAtual(ISocket painelAtual) {
        ManterServer.painelAtual = painelAtual;
    }

    public static ServerSocket getServidor() {
        return servidor;
    }

    public static void setServidor(ServerSocket servidor) {
        ManterServer.servidor = servidor;
    }

    public static Socket getCliente() {
        return cliente;
    }

    public static void setCliente(Socket cliente) {
        ManterServer.cliente = cliente;
    }

    public static ObjectInputStream getOis() {
        return ois;
    }

    public static void setOis(ObjectInputStream ois) {
        ManterServer.ois = ois;
    }

    public static void iniciarServer() {
        iniciar();
    }

    private static void iniciar() {
        Thread t = new Thread() {
            public void run() {

                try {
                    servidor = new ServerSocket(Integer.parseInt(ManterIp.getIpServidor().getPorta()));
                    cliente = null;

                    while (true) {
                        String texto = "";
                        cliente = servidor.accept();
                        ois = new ObjectInputStream(cliente.getInputStream());
                        Object objeto = ois.readObject();
                        painelAtual.recebeObjeto(objeto);
                        if (texto.equals("quit")) {
                            break;
                        }
                    }
                    servidor.close();
                    cliente.close();
                    ois.close();
                    System.out.println("Servidor encerrado!");
                } catch (Exception ex) {

                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }

}
