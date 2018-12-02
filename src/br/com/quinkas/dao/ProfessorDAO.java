/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao;

import br.com.quinkas.entidade.Professor;
import java.util.List;

/**
 *
 * @author erick
 */
public interface ProfessorDAO {
    Integer insert(Professor professor) throws Exception;
    
    Professor select(Integer id) throws Exception;
    
    Boolean emailExiste(String email) throws Exception;
    
    void update(Professor professor) throws Exception;
    
    void delete(Integer id) throws Exception;
    
    List<Professor> list(String termo) throws Exception;
    
    List<Professor> listAll() throws Exception;
    
    Professor validarLogin(String email, String senha) throws Exception;
}
