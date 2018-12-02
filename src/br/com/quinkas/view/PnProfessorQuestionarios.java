/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.view;

import br.com.quinkas.manter.ManterPrincipal;
import br.com.quinkas.manter.ManterProfessor;
import br.com.quinkas.util.CorPainel;

/**
 *
 * @author Felipe-Sistema
 */
public class PnProfessorQuestionarios extends javax.swing.JPanel {

    /**
     * Creates new form PnProfessorQuestoes
     */
    public PnProfessorQuestionarios() {
        initComponents();
        CorPainel altera = new CorPainel(this);
        Thread t = new Thread(altera);
        t.start();
        addQuestionario("Questionário nível fácil de Java", "11/11/2011");
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnQuestionarioNovo = new javax.swing.JPanel();
        lbNomeNovo = new javax.swing.JLabel();
        lbDataNovo = new javax.swing.JLabel();
        lbRetorno = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));

        setMinimumSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/logo_prof.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        add(jLabel1, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 250));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnQuestionarioNovo.setBackground(new java.awt.Color(204, 204, 204));
        pnQuestionarioNovo.setForeground(new java.awt.Color(51, 51, 51));
        pnQuestionarioNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnQuestionarioNovo.setMinimumSize(new java.awt.Dimension(200, 150));
        pnQuestionarioNovo.setPreferredSize(new java.awt.Dimension(200, 150));
        pnQuestionarioNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnQuestionarioNovoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnQuestionarioNovoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnQuestionarioNovoMousePressed(evt);
            }
        });
        pnQuestionarioNovo.setLayout(new java.awt.GridBagLayout());

        lbNomeNovo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNomeNovo.setForeground(new java.awt.Color(0, 0, 0));
        lbNomeNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/novo.png"))); // NOI18N
        lbNomeNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnQuestionarioNovo.add(lbNomeNovo, new java.awt.GridBagConstraints());

        lbDataNovo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDataNovo.setForeground(new java.awt.Color(102, 102, 102));
        lbDataNovo.setText("Novo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnQuestionarioNovo.add(lbDataNovo, gridBagConstraints);

        jPanel1.add(pnQuestionarioNovo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.2;
        add(jPanel1, gridBagConstraints);

        lbRetorno.setForeground(new java.awt.Color(255, 255, 255));
        lbRetorno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/retornar.png"))); // NOI18N
        lbRetorno.setText("Retornar ");
        lbRetorno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbRetorno.setInheritsPopupMenu(false);
        lbRetorno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbRetornoMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        add(lbRetorno, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Desenvolvido pelos alunos: Érick, Felipe, Luiza e Robson.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        add(jLabel3, gridBagConstraints);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/quinkas/imagem/senac_logo.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.1;
        add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.1;
        add(filler5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(filler7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        add(filler8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        add(filler9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        add(filler10, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void lbRetornoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRetornoMousePressed
        ManterProfessor.deslogar();
        PnProfessor pnl = new PnProfessor();
        ManterPrincipal.getPrincipal().setContentPane(pnl);
        ManterPrincipal.getPrincipal().setVisible(true);
    }//GEN-LAST:event_lbRetornoMousePressed

    private void pnQuestionarioNovoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuestionarioNovoMouseEntered
        pnQuestionarioNovo.setBackground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_pnQuestionarioNovoMouseEntered

    private void pnQuestionarioNovoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuestionarioNovoMouseExited
        pnQuestionarioNovo.setBackground(new java.awt.Color(204, 204, 204));
    }//GEN-LAST:event_pnQuestionarioNovoMouseExited

    private void pnQuestionarioNovoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuestionarioNovoMousePressed
        PnProfessorQuestionario pn1 = new PnProfessorQuestionario();
        ManterPrincipal.getPrincipal().setContentPane(pn1);
        ManterPrincipal.getPrincipal().setVisible(true);
    }//GEN-LAST:event_pnQuestionarioNovoMousePressed

    private void addQuestionario(String nome, String data) {
        java.awt.GridBagConstraints gridBagConstraints;
        javax.swing.JPanel pnQuestionario = new javax.swing.JPanel();
        javax.swing.JLabel lbNome = new javax.swing.JLabel();
        javax.swing.JLabel lbData = new javax.swing.JLabel();

        pnQuestionario.setBackground(new java.awt.Color(204, 204, 204));
        pnQuestionario.setForeground(new java.awt.Color(51, 51, 51));
        pnQuestionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnQuestionario.setMinimumSize(new java.awt.Dimension(200, 150));
        pnQuestionario.setPreferredSize(new java.awt.Dimension(200, 150));
        pnQuestionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnQuestionario.setBackground(new java.awt.Color(153, 153, 153));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnQuestionario.setBackground(new java.awt.Color(204, 204, 204));
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                PnProfessorQuestionario pn1 = new PnProfessorQuestionario();
                ManterPrincipal.getPrincipal().setContentPane(pn1);
                ManterPrincipal.getPrincipal().setVisible(true);
            }
        });
        pnQuestionario.setLayout(new java.awt.GridBagLayout());

        lbNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbNome.setForeground(new java.awt.Color(0, 0, 0));
        lbNome.setText("<html>" + nome + "</html>");
        lbNome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnQuestionario.add(lbNome, new java.awt.GridBagConstraints());

        lbData.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbData.setForeground(new java.awt.Color(102, 102, 102));
        lbData.setText(data);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnQuestionario.add(lbData, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
      //  pnQuestionario.add(filler2, gridBagConstraints);

        jPanel1.add(pnQuestionario);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbDataNovo;
    private javax.swing.JLabel lbNomeNovo;
    private javax.swing.JLabel lbRetorno;
    private javax.swing.JPanel pnQuestionarioNovo;
    // End of variables declaration//GEN-END:variables
}
