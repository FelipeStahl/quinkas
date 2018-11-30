/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao;


import br.com.quinkas.entidade.Questionario;
import java.util.List;

/**
 *
 * @author erick
 */
public interface QuestionarioDAO {
    Integer insert(Questionario questionario) throws Exception;
    
    Questionario select(Integer id) throws Exception;
    
    void update(Questionario questionario) throws Exception;
    
    void updatePerguntas(Questionario questionario) throws Exception;
    
    void delete(Integer id) throws Exception;

    List<Questionario> listPorProfessor(Integer idProfessor) throws Exception;
    
    List<Questionario> listPorPergunta(Integer idPergunta) throws Exception;
    
    List<Questionario> listPorMateria(Integer idMateria) throws Exception;
    
    List<Questionario> listAll() throws Exception;
}
