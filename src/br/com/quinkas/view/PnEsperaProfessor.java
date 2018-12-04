/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.view;

import br.com.quinkas.conexao.EnviaSocket;
import br.com.quinkas.conexao.ISocket;
import br.com.quinkas.conexao.Server;
import br.com.quinkas.entidade.IpAndPorta;
import br.com.quinkas.entidade.Participante;
import br.com.quinkas.entidade.Pergunta;
import br.com.quinkas.entidade.Questionario;
import br.com.quinkas.estrutura.ListaEncadeada;
import br.com.quinkas.manter.ManterIp;
import br.com.quinkas.manter.ManterLista;
import br.com.quinkas.manter.ManterParticipante;
import br.com.quinkas.manter.ManterPrincipal;
import br.com.quinkas.manter.ManterServer;
import br.com.quinkas.util.CorPainel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe-Sistema
 */
public class PnEsperaProfessor extends javax.swing.JPanel implements ISocket {

    Questionario questionarioAtual;
    Integer numJogadores;

    public PnEsperaProfessor(Questionario questionario) {
        initComponents();
        CorPainel altera = new CorPainel(this);
        Thread t = new Thread(altera);
        t.start();

        numJogadores = 0;
        questionarioAtual = questionario;
        ListaEncadeada lista = new ListaEncadeada();
        for (Pergunta pergunta : questionarioAtual.getPerguntas()) {
            lista.adicionar(pergunta);
        }
        ManterLista.setLista(lista);

        String ipAtual = retornarIp();
        if (ipAtual != null) {
            IpAndPorta ipServidor = new IpAndPorta();
            ipServidor.setIp(ipAtual);
            ipServidor.setPorta("157");
            ManterIp.setIpServidor(ipServidor);
            lbPin.setText(ManterIp.converterPin(ipServidor));
        }
        ManterServer.setPainelAtual(this);
        ManterServer.iniciarServer();
    }

    private String retornarIp() {
        InetAddress ipAtual;
        try {
            ipAtual = InetAddress.getLocalHost();
            System.out.println(ipAtual.getHostAddress());
            return ipAtual.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(PnEsperaProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void addJogador(String nome) {
        javax.swing.JLabel lbJogador = new javax.swing.JLabel();
        lbJogador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbJogador.setForeground(new java.awt.Color(0, 0, 0));
        lbJogador.setText(nome);
        javax.swing.Box.Filler espaceJog = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(0, 0));
        jPanel2.add(lbJogador);
        jPanel2.add(espaceJog);
        numJogadores++;
        this.lbJogadores.setText("Jogadores: " + numJogadores.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbJogadores = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbPin = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        btEntrar = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));

        setBackground(new java.awt.Color(0, 0, 102));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(jLabel1, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jPanel2, gridBagConstraints);

        lbJogadores.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbJogadores.setForeground(new java.awt.Color(255, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lbJogadores, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 255, 255));
        jLabel3.setText("PIN:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel3, gridBagConstraints);

        lbPin.setFont(new java.awt.Font("Tahoma", 1, 120)); // NOI18N
        lbPin.setForeground(new java.awt.Color(153, 255, 255));
        lbPin.setText(".");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(lbPin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(filler6, gridBagConstraints);

        btEntrar.setBackground(new java.awt.Color(4, 12, 167));
        btEntrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btEntrar.setText("INICIAR");
        btEntrar.setBorder(null);
        btEntrar.setBorderPainted(false);
        btEntrar.setContentAreaFilled(false);
        btEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEntrar.setFocusPainted(false);
        btEntrar.setMaximumSize(new java.awt.Dimension(83, 40));
        btEntrar.setMinimumSize(new java.awt.Dimension(83, 40));
        btEntrar.setOpaque(true);
        btEntrar.setPreferredSize(new java.awt.Dimension(51, 40));
        btEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEntrarMouseClicked(evt);
            }
        });
        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(btEntrar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        jPanel1.add(filler7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(filler5, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEntrarMouseClicked
        //        FrameNick nick = new FrameNick(this.jframe, jTextField1.getText());
        //        this.jframe.setContentPane(nick);
        //        this.jframe.setVisible(true);
    }//GEN-LAST:event_btEntrarMouseClicked

    private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntrarActionPerformed
        iniciarJogo();
        PnQuestaoInicial pn1 = new PnQuestaoInicial();
        ManterPrincipal.getPrincipal().setContentPane(pn1);
        ManterPrincipal.getPrincipal().setVisible(true);
// ERRO DE PIN:
        // erroPin();
    }//GEN-LAST:event_btEntrarActionPerformed

    private void iniciarJogo(){
        for (Map.Entry<String, Participante> entry : ManterParticipante.getParticipantes().entrySet()) {
            Participante participante = entry.getValue();
            Boolean iniciar = true;
            EnviaSocket.enviarObjeto(iniciar, participante);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEntrar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbJogadores;
    private javax.swing.JLabel lbPin;
    // End of variables declaration//GEN-END:variables

    @Override
    public void recebeObjeto(Object objeto) {
        try {
            if (objeto instanceof String) {
                System.out.println((String)objeto);
            }else if(objeto instanceof Participante) {
                addJogador(((Participante)objeto).getNick());
                ManterParticipante.addParticipante((Participante)objeto);
                EnviaSocket.enviarObjeto(ManterLista.getLista(), (Participante)objeto);
            }
            
        } catch (Exception e) {

        }

    }
}
