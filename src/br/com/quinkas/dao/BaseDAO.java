/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao;

import java.util.List;

/**
 *
 * @author Felipe-Sistema
 */
public interface BaseDAO {
    Integer inserir(Object objeto) throws Exception;
    
    Integer alterar(Object objeto) throws Exception;
    
    Object select(Integer id) throws Exception;     
    
    void delete(Integer id) throws Exception;

    void excluirDependente(Integer id) throws Exception;
    
    List pesquisar(String termo) throws Exception;
    
    List pesquisar(Object objeto) throws Exception;

}
