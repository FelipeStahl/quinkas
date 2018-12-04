/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.view;

import br.com.quinkas.entidade.Pergunta;
import br.com.quinkas.manter.ManterLista;
import br.com.quinkas.manter.ManterPergunta;
import br.com.quinkas.manter.ManterPrincipal;
import br.com.quinkas.manter.ManterProfessor;
import br.com.quinkas.util.CorPainel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe-Sistema
 */
public class PnQuestaoInicial extends javax.swing.JPanel {

    private Integer tempo;

    /**
     * Creates new form PnQuestaoInicial
     */
    public PnQuestaoInicial() {
        initComponents();
        tempo = 5;

        CorPainel altera = new CorPainel(jPanel1);
        Thread t = new Thread(altera);
        t.start();
        ManterPergunta.setPerguntaAtual((Pergunta) ManterLista.getLista().getProximo());
        if (ManterPergunta.getPerguntaAtual() == null) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    PnJogoFinal pn1 = new PnJogoFinal();
                    ManterPrincipal.getPrincipal().setContentPane(pn1);
                    ManterPrincipal.getPrincipal().setVisible(true);
                }
            });
        } else {
            lbPergunta.setText("<html>" + ManterPergunta.getPerguntaAtual().getPergunta() + "</html>");
            contagem();
        }
    }

    private void contagem() {
        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < tempo; i++) {
                    Integer resta = tempo - i;
                    lbTempo.setText(resta.toString());
                    lbTempo.repaint();
                    lbTempo.revalidate();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PnQuestaoInicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (ManterProfessor.getProfessor() == null) {
                    PnQuestao pn1 = new PnQuestao();
                    ManterPrincipal.getPrincipal().setContentPane(pn1);
                    ManterPrincipal.getPrincipal().setVisible(true);
                }else{
                    PnQuestaoProfessor pn1 = new PnQuestaoProfessor();
                    ManterPrincipal.getPrincipal().setContentPane(pn1);
                    ManterPrincipal.getPrincipal().setVisible(true);
                }

            }
        }.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbPergunta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbTempo = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setMinimumSize(new java.awt.Dimension(799, 150));
        jPanel1.setPreferredSize(new java.awt.Dimension(799, 150));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.2;
        add(jPanel1, gridBagConstraints);

        lbPergunta.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbPergunta.setForeground(new java.awt.Color(0, 0, 0));
        lbPergunta.setText("Pergunta?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        add(lbPergunta, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("TEMPO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(jLabel3, gridBagConstraints);

        lbTempo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTempo.setForeground(new java.awt.Color(0, 102, 102));
        lbTempo.setText("10s");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(lbTempo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.1;
        add(filler8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 0.1;
        add(filler9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        add(filler10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        add(filler11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        add(filler12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(filler13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        add(filler14, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbPergunta;
    private javax.swing.JLabel lbTempo;
    // End of variables declaration//GEN-END:variables
}
