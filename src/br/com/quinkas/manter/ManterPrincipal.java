/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.view.Principal;

/**
 *
 * @author Felipe-Sistema
 */
public class ManterPrincipal {
    private static Principal principal;

    public static Principal getPrincipal() {
        return principal;
    }

    public static void setPrincipal(Principal principal) {
        ManterPrincipal.principal = principal;
    }
    
}
