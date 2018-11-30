/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.AlternativaDAO;
import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.dao.MateriaDAO;
import br.com.quinkas.dao.PerguntaDAO;
import br.com.quinkas.dao.QuestionarioDAO;
import br.com.quinkas.entidade.Alternativa;
import br.com.quinkas.entidade.Materia;
import br.com.quinkas.entidade.Pergunta;
import br.com.quinkas.entidade.Questionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erick
 */
public class QuestionarioPerguntaDAOImpl implements br.com.quinkas.dao.QuestionarioPerguntaDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public void insert(Questionario questionario, Pergunta pergunta) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("insert into questionario_pergunta (Questionario_id, Pergunta_id) values (?, ?)");
            ps.setInt(1, questionario.getId());
            ps.setInt(2, pergunta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Não foi possivel inserir relação entre Questionário e Pergunta" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public List<Pergunta> selectPerguntas(Integer idQuestionario) throws Exception {
        List<Pergunta> listaPerguntas = new ArrayList<Pergunta>();
        //PerguntaDAO perguntaDao = new PerguntaDAOImpl();
        List<Integer> idPerguntas = new ArrayList<Integer>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select Pergunta_id from questionario_pergunta where Questionario_id=?");
            ps.setInt(1, idQuestionario);
            rs = ps.executeQuery();
            while (rs.next()) {
                idPerguntas.add(rs.getInt("Pergunta_id"));
            }
            for (Integer idPergunta : idPerguntas) {
                Pergunta pergunta = new Pergunta();
                Materia materia = new Materia();
                List<Alternativa> alternativas = new ArrayList<Alternativa>();
                ps = conn.prepareStatement("select id, pergunta, escopo, materia_id, imagepath from pergunta where id=?");
                ps.setInt(1, idPergunta);
                rs = ps.executeQuery();
                if (rs.next()) {
                    pergunta.setId(rs.getInt("id"));
                    pergunta.setPergunta(rs.getString("pergunta"));
                    pergunta.setEscopo(rs.getString("escopo"));
                    pergunta.setImagePath(rs.getString("imagepath"));
                    ps = conn.prepareStatement("select id, nome from materia where id=?");
                    ps.setInt(1, rs.getInt("Materia_id"));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        materia.setId(rs.getInt("id"));
                        materia.setNome(rs.getString("nome"));
                    }
                    pergunta.setMateria(materia);
                    ps = conn.prepareStatement("select id, alternativa, resposta, isTrue, Pergunta_id from alternativa where Pergunta_id=? order by alternativa");
                    ps.setInt(1, idPergunta);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Alternativa alternativa = new Alternativa();
                        alternativa.setId(rs.getInt("id"));
                        alternativa.setAlternativa(rs.getInt("alternativa"));
                        alternativa.setResposta(rs.getString("resposta"));
                        alternativa.setIsTrue(rs.getBoolean("isTrue"));
                        alternativa.setPergunta(pergunta);
                        alternativas.add(alternativa);
                    }
                    pergunta.setAlternativas(alternativas);
                    listaPerguntas.add(pergunta);
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel resgatar lista de Perguntas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return listaPerguntas;
    }

    @Override
    public List<Questionario> selectQuestionarios(Integer idPergunta) throws Exception {
        List<Questionario> listaQuestionarios = new ArrayList<Questionario>();
        QuestionarioDAO questionarioDao = new QuestionarioDAOImpl();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select Questionario_id from questionario_pergunta where Pergunta_id=?");
            ps.setInt(1, idPergunta);
            rs = ps.executeQuery();
            while (rs.next()) {
                listaQuestionarios.add(questionarioDao.select(rs.getInt("Questionario_id")));
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel resgatar lista de Questionarios" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return listaQuestionarios;
    }

    @Override
    public void delete(Questionario questionario, Pergunta pergunta) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("delete from questionario_pergunta where Pergunta_id=? and Questionario_id=?");
            ps.setInt(1, pergunta.getId());
            ps.setInt(2, questionario.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar relação entre questionario e pergunta" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void deleteAllPergunta(Integer perguntaId) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("delete from questionario_pergunta where Pergunta_id=?");
            ps.setInt(1, perguntaId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar relações desta pergunta" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void deleteAllQuestionario(Integer questionarioId) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("delete from questionario_pergunta where Questionario_id=?");
            ps.setInt(1, questionarioId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar relações deste questionario" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

}
