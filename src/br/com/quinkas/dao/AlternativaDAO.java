/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao;

import br.com.quinkas.entidade.Alternativa;
import java.util.List;

/**
 *
 * @author erick
 */
public interface AlternativaDAO {
    Integer insert(Alternativa alternativa) throws Exception;
    
    Alternativa select(Integer id) throws Exception;
    
    void update(Alternativa alternativa) throws Exception;
    
    void delete(Integer id) throws Exception;
    
    List<Alternativa> list(String termo) throws Exception;
    
    List<Alternativa> listPorPergunta(Integer idPergunta) throws Exception;
    
    List<Alternativa> listAll() throws Exception;
}
