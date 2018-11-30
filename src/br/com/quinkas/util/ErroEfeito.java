/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe-Sistema
 */
public class ErroEfeito extends Thread {
    private javax.swing.JPanel painel;
    
    public ErroEfeito(javax.swing.JPanel panel){
        painel = panel;
    }
    @Override
    public void run() {
        painel.setVisible(true);
        painel.setPreferredSize(new java.awt.Dimension(600, 0));
        for (int i = 0; i <= 40; i++) {            
            try {
                painel.setPreferredSize(new java.awt.Dimension(600, i));
                painel.repaint();
                painel.revalidate();
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(ErroEfeito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ErroEfeito.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 40; i >= 0; i--) {            
            try {
                painel.setPreferredSize(new java.awt.Dimension(600, i));
                painel.repaint();
                painel.revalidate();
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(ErroEfeito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        painel.setVisible(false);
    }
}
