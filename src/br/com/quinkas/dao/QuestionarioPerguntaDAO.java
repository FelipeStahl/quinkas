/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao;

import br.com.quinkas.entidade.Pergunta;
import br.com.quinkas.entidade.Questionario;
import java.util.List;

/**
 *
 * @author erick
 */
public interface QuestionarioPerguntaDAO {
    
    void insert(Questionario questionario, Pergunta pergunta) throws Exception;
    
    List<Pergunta> selectPerguntas(Integer idQuestionario) throws Exception;
    
    List<Questionario> selectQuestionarios(Integer idPergunta) throws Exception;
    
    void delete(Questionario questionario, Pergunta pergunta) throws Exception;
    
    void deleteAllPergunta(Integer perguntaId) throws Exception;
    
    void deleteAllQuestionario(Integer questionarioId) throws Exception;
}
