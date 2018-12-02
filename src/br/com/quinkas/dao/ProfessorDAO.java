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
public interface ProfessorDAO extends BaseDAO {

    Boolean emailExiste(String email) throws Exception;

    Professor validarLogin(String email, String senha) throws Exception;
}
