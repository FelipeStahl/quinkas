/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.entidade.Alternativa;
import br.com.quinkas.entidade.Pergunta;
import br.com.quinkas.entidade.Questionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erick
 */
public class PerguntaDAOImpl implements br.com.quinkas.dao.PerguntaDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public Integer inserir(Object objeto) throws Exception {
        try {
            Pergunta pergunta = (Pergunta) objeto;
            if (pergunta.getId() != null) {
                conn = ConnectionFactory.getConnection();
                ps = conn.prepareStatement("insert into pergunta (pergunta, questionario_id) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, pergunta.getPergunta());
                ps.setInt(2, pergunta.getQuestionario().getId());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int ultimoId = rs.getInt(1);
                    return ultimoId;
                } else {
                    return null;
                }
            } else {
                return alterar(pergunta);
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Erro ao inserir questionario. " + e.getMessage());
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public Integer alterar(Object objeto) throws Exception {
        try {
            Pergunta pergunta = (Pergunta) objeto;
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update pergunta set nome = ?, questionario_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pergunta.getPergunta());
            ps.setInt(2,pergunta.getQuestionario().getId());
            ps.setInt(3, pergunta.getId());
            int executeUpdate = ps.executeUpdate();
            if (executeUpdate != 0) {
                return pergunta.getId();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Erro ao alterar questionario. " + e.getMessage());
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public Object select(Integer id) throws Exception {
        throw new UnsupportedOperationException("Este método não está disponível na versão grátis.");
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            excluirDependente(id);
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("DELETE FROM pergunta where id = ?;");
            ps.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    @Override
    public List pesquisar(String termo) throws Exception {
        throw new UnsupportedOperationException("Este método não está disponível na versão grátis.");
    }

    @Override
    public List pesquisar(Object objeto) throws Exception {
        List<Pergunta> perguntas = new ArrayList();
        Questionario questionario = (Questionario)objeto;
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, pergunta, questionario_id from pergunta where questionario_id=?");
            ps.setInt(1, questionario.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Pergunta pergunta = new Pergunta();               
                pergunta.setId(rs.getInt("id"));
                pergunta.setPergunta(rs.getString("nome"));
                pergunta.setQuestionario(questionario);

                AlternativaDAOImpl alternativaDao = new AlternativaDAOImpl();
                pergunta.setAlternativas(alternativaDao.pesquisar(pergunta));
                perguntas.add(pergunta);
            }
            return perguntas;
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar perguntas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return null;
    }
    
    @Override
    public void excluirDependente(Integer id) throws Exception {
        try {
            AlternativaDAOImpl alternativaDao = new AlternativaDAOImpl();
            List<Alternativa> alternativas = alternativaDao.pesquisar(id);
            for (Alternativa alternativa : alternativas) {
                alternativaDao.delete(alternativa.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
