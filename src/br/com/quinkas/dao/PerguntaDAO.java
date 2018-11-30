/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao;

import br.com.quinkas.entidade.Pergunta;
import java.util.List;

/**
 *
 * @author erick
 */
public interface PerguntaDAO {
    Integer insert(Pergunta pergunta) throws Exception;
    
    Pergunta select(Integer id) throws Exception;
    
    void update(Pergunta pergunta) throws Exception;
    
    void delete(Integer id) throws Exception;
    
    List<Pergunta> list(String termo) throws Exception;
    
    List<Pergunta> listPorMateria(Integer idMateria) throws Exception;
    
    List<Pergunta> listPorQuestionario(Integer idQuestionario) throws Exception;
    
    List<Pergunta> listAll() throws Exception;
}
