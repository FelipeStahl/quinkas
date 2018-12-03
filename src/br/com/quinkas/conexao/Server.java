/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe-Sistema
 */
public class Server extends Thread {

    private String porta;
    private ISocket painelAtual;

    public Server(ISocket painel) {
        painelAtual = painel;
    }

    @Override
    public void run() {
        ServerSocket servidor;
        try {
            servidor = new ServerSocket(8787);
            System.out.println("Servidor iniciado...");
            Socket cliente = null;
            ObjectInputStream ois;          
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

//    private void enviarObjeto(Object objeto){
//        Socket cliente;
//        try {
//            //cliente = new Socket("localhost", ((Usuario)objeto).getPorta()); //192.168.56.1
//            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
//            oos.writeObject(objeto);
//            cliente.close();
//            oos.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);            
//        } 
//    }
//        private void enviarObjeto(Usuario destinatario, Object objeto) {
//        Socket cliente;
//        try {
//            cliente = new Socket("localhost", destinatario.getPorta()); //192.168.56.1
//            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
//            oos.writeObject(objeto);
//            cliente.close();
//            oos.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
