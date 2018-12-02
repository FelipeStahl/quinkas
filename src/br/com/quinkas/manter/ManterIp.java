/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.entidade.IpAndPorta;

/**
 *
 * @author Felipe-Sistema
 */
public class ManterIp {
    private static IpAndPorta ipServidor;
    private static IpAndPorta meuIp;
    private static String LETRAS = "abcdefghijklmnopqrstuvwxyz";
    
    public static IpAndPorta getIpServidor() {
        return ipServidor;
    }

    public static void setIpServidor(IpAndPorta ipServidor) {
        ManterIp.ipServidor = ipServidor;
    }

    public static IpAndPorta getMeuIp() {
        return meuIp;
    }

    public static void setMeuIp(IpAndPorta meuIp) {
        ManterIp.meuIp = meuIp;
    }

    public static String converterPin(String ip){
        String pin = "";
        String[] ips = ip.split("[.]");
        
        for (int i = 0; i < ips.length; i++) {
            // System.out.println(ips[i]);
            String primeiro = ips[i].substring(0, ips[i].length() - 1);
            String ultimo = ips[i].substring(ips[i].length() - 1, ips[i].length());
            if (!primeiro.equals("")) {
                Integer num = Integer.parseInt(primeiro);
                if (num <= 25) {
                    pin += LETRAS.charAt(num);
                } else {
                    Integer num1 = Integer.parseInt(primeiro.substring(0, 1));
                    Integer num2 = Integer.parseInt(primeiro.substring(1, 1));
                    pin += LETRAS.charAt(num1);
                    pin += LETRAS.charAt(num2);
                }
            }
            pin += ultimo;           
        }
        return pin;
    }
    
    public static void reverterPin(String pin){
        String ip = "";
        char[] pins = pin.toCharArray();
        for (int i = 0; i < pins.length; i++) {
            if(Character.isLetter(pins[i])){
                ip += LETRAS.indexOf(pins[i]);
            }else{
                ip += pins[i] + ".";
            }
        } 
        ip = ip.substring(0, ip.length() - 1);
        String porta = ip.substring(ip.lastIndexOf(".") +1, ip.length());
        ip = ip.substring(0, ip.lastIndexOf("."));
        IpAndPorta servidor = new IpAndPorta();
        servidor.setIp(ip);
        servidor.setPorta(porta);
        setIpServidor(servidor);
    }        
}
