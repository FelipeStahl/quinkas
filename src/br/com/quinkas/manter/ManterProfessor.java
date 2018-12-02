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
    private static Professor PROFESSOR;
    private static ProfessorDAOImpl professorDao;
    
    public static Professor getProfessor() {
        return PROFESSOR;
    }
        
    public static Boolean validarLogin(String email, String senha){
        professorDao = new ProfessorDAOImpl();
        Professor professorLogin;
        try {
            professorLogin = professorDao.validarLogin(email, senha);
        } catch (Exception ex) {
            return false;
        }
        if(professorLogin == null){
            return false;
        }else{
            PROFESSOR = professorLogin;
            return true;
        }       
    }
    
    public static Boolean emailExiste(String email){
        professorDao = new ProfessorDAOImpl();
        try {
            return professorDao.emailExiste(email);
        } catch (Exception ex) {
            Logger.getLogger(ManterProfessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static void cadastrarProfessor(Professor professor) throws Exception{
        professorDao = new ProfessorDAOImpl();
        try {
            if(professor.getId() == null){
                professor.setId(professorDao.insert(professor));
               PROFESSOR = professor;
            }else{
                professorDao.update(professor);
                PROFESSOR = professor;
            }
        } catch (Exception ex) {
            throw new UnsupportedOperationException("Erro ao cadastrar/alterar professor." + ex.getMessage());
        }
    }
    
    public static void deslogar(){
        PROFESSOR = null;
    }
}
