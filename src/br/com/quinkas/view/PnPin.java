/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.view;

import br.com.quinkas.entidade.IpAndPorta;
import br.com.quinkas.estrutura.ListaEncadeada;
import br.com.quinkas.manter.ManterIp;
import br.com.quinkas.manter.ManterLista;
import br.com.quinkas.manter.ManterPrincipal;
import br.com.quinkas.util.CorPainel;
import br.com.quinkas.util.ErroEfeito;
import java.awt.Graphics;
import java.awt.Image;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 *
 * @author Felipe-Sistema
 */
public class PnPin extends javax.swing.JPanel {

    /**
     * Creates new form pnPIN
     */
    public PnPin() {
        initComponents();
        pnErro.setVisible(false);
        CorPainel altera = new CorPainel(this);
        Thread t = new Thread(altera);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        txPin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 10));
        lbProfessor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(78, 0), new java.awt.Dimension(78, 0), new java.awt.Dimension(78, 32767));
        pnErro = new javax.swing.JPanel();
        lbMensagemErro = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(319, 180));

        txPin.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        txPin.setForeground(new java.awt.Color(0, 102, 204));
        txPin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txPin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Digite um PIN para entrar");

        btEntrar.setBackground(new java.awt.Color(4, 12, 167));
        btEntrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btEntrar.setText("ENTRAR");
        btEntrar.setBorder(null);
        btEntrar.setBorderPainted(false);
        btEntrar.setContentAreaFilled(false);
        btEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEntrar.setFocusPainted(false);
        btEntrar.setOpaque(true);
        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(txPin, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(btEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txPin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        add(jPanel1, gridBagConstraints);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        add(filler1, gridBagConstraints);

        lbProfessor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbProfessor.setForeground(new java.awt.Color(51, 255, 255));
        lbProfessor.setText("Você é professor? Clique aqui!");
        lbProfessor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbProfessor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbProfessorMouseMoved(evt);
            }
        });
        lbProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbProfessorMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbProfessorMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        add(lbProfessor, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Desenvolvido pelos alunos: Érick, Felipe, Luiza e Robson.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        add(jLabel3, gridBagConstraints);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/senac_logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 2, 0);
        add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.1;
        add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 0.1;
        add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 0.1;
        add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        add(filler6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        add(filler8, gridBagConstraints);

        pnErro.setBackground(new java.awt.Color(102, 0, 0));
        pnErro.setMinimumSize(new java.awt.Dimension(0, 0));
        pnErro.setPreferredSize(new java.awt.Dimension(600, 40));
        pnErro.setLayout(new java.awt.GridBagLayout());

        lbMensagemErro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbMensagemErro.setForeground(new java.awt.Color(255, 255, 255));
        lbMensagemErro.setText("PIN INVÁLIDO!");
        pnErro.add(lbMensagemErro, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(pnErro, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void lbProfessorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProfessorMouseMoved
        lbProfessor.setForeground(new java.awt.Color(20, 205, 255));
    }//GEN-LAST:event_lbProfessorMouseMoved

    private void lbProfessorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProfessorMouseExited
        lbProfessor.setForeground(new java.awt.Color(51, 255, 255));
    }//GEN-LAST:event_lbProfessorMouseExited

    private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntrarActionPerformed
        String pin = txPin.getText().trim();
        if (!pin.equals("")) {
            IpAndPorta ipServidor = new IpAndPorta();
            ipServidor = ManterIp.reverterPin(pin);

            if (testeConexao(ipServidor)) {
                ManterIp.setIpServidor(ipServidor);
                PnNick pn1 = new PnNick();
                ManterPrincipal.getPrincipal().setContentPane(pn1);
                ManterPrincipal.getPrincipal().setVisible(true);
            } else {
                erroPin();
            }
        } else {
            erroPin();
        }

    }//GEN-LAST:event_btEntrarActionPerformed

    private Boolean testeConexao(IpAndPorta ipServidor){
        Socket cliente;
        try {
            cliente = new Socket(ipServidor.getIp(), Integer.parseInt(ipServidor.getPorta()));
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject("oii");
            cliente.close();
            oos.close();
            return true;
        } catch (Exception ex) {
            System.out.println("erro");
            return false;
        }
    }
    
    private void receberLista(ListaEncadeada lista) {
        ManterLista.setLista(lista);
    }
    
    private void lbProfessorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProfessorMousePressed
        PnProfessor pn = new PnProfessor();
        ManterPrincipal.getPrincipal().setContentPane(pn);
        ManterPrincipal.getPrincipal().setVisible(true);
    }//GEN-LAST:event_lbProfessorMousePressed

    private void erroPin() {
        ErroEfeito altera = new ErroEfeito(pnErro);
        Thread t = new Thread(altera);
        t.start();
        txPin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(152, 0, 0)));
        txPin.setText("");
        txPin.requestFocus();
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
    private javax.swing.Box.Filler filler8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbMensagemErro;
    private javax.swing.JLabel lbProfessor;
    private javax.swing.JPanel pnErro;
    private javax.swing.JTextField txPin;
    // End of variables declaration//GEN-END:variables
}
