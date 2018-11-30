/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao;

import br.com.quinkas.entidade.Materia;
import br.com.quinkas.entidade.Questionario;
import java.util.List;

/**
 *
 * @author erick
 */
public interface MateriaDAO {
    
    Integer insert(Materia materia) throws Exception;
    
    Materia select(Integer id) throws Exception;
    
    void update(Materia materia) throws Exception;
    
    //void delete(Integer id) throws Exception;
    
    List<Materia> list(String termo) throws Exception;
    
    List<Materia> listPorQuestionario(Integer idQuestionario) throws Exception;
    
    List<Materia> listAll() throws Exception;
}
