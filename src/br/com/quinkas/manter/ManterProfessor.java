/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.dao.impl.ProfessorDAOImpl;
import br.com.quinkas.entidade.Professor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe-Sistema
 */
public class ManterProfessor {
    private static Professor professor;

    public static Professor getProfessor() {
        return professor;
    }
        
    public static Boolean validarLogin(String email, String senha){
        ProfessorDAOImpl professorDao = new ProfessorDAOImpl();
        Professor professorLogin;
        try {
            professorLogin = professorDao.validarLogin(email, senha);
        } catch (Exception ex) {
            return false;
        }
        if(professorLogin == null){
            return false;
        }else{
            return true;
        }       
    }
}
