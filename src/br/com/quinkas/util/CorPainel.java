package br.com.quinkas.util;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CorPainel extends Thread {

    private javax.swing.JPanel painel;
    private Integer r;
    private Integer g;
    private Integer b;

    public CorPainel(javax.swing.JPanel panel) {
        painel = panel;
        r = 0;
        g = 50;
        b = 140;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Random rand = new Random();
                Integer rN = rand.nextInt(255);
                if (rN > r) {
                    for (int i = r; i < rN; i++) {
                        r++;
                        painel.setBackground(new java.awt.Color(r, g, b));
                        Thread.sleep(100);
                    }
                }else{
                    for (int i = rN; i < r; i++) {
                        r--;
                        painel.setBackground(new java.awt.Color(r, g, b));
                        Thread.sleep(100);
                    }
                }
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CorPainel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
