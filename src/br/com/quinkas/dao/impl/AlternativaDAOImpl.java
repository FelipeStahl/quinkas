/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.dao.PerguntaDAO;
import br.com.quinkas.entidade.Alternativa;
import br.com.quinkas.entidade.Pergunta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erick
 */
public class AlternativaDAOImpl implements br.com.quinkas.dao.AlternativaDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Integer inserir(Object objeto) throws Exception {
        try {
            Alternativa alternativa = (Alternativa) objeto;
            if (alternativa.getId() != null) {
                conn = ConnectionFactory.getConnection();
                ps = conn.prepareStatement("insert into alternativa (resposta, isTrue, pergunta_id) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, alternativa.getResposta());
                ps.setBoolean(2, alternativa.getIsTrue());
                ps.setInt(3, alternativa.getPergunta().getId());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int ultimoId = rs.getInt(1);
                    return ultimoId;
                } else {
                    return null;
                }
            } else {
                return alterar(alternativa);
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Erro ao inserir alternativa. " + e.getMessage());
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public Integer alterar(Object objeto) throws Exception {
        try {
            Alternativa alternativa = (Alternativa) objeto;
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update alternativa set resposta = ?, isTrue, pergunta_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alternativa.getResposta());
            ps.setBoolean(2, alternativa.getIsTrue());
            ps.setInt(3, alternativa.getPergunta().getId());
            ps.setInt(4, alternativa.getId());
            int executeUpdate = ps.executeUpdate();
            if (executeUpdate != 0) {
                return alternativa.getId();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Erro ao alterar alternativa. " + e.getMessage());
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public Object select(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws Exception {
       try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("DELETE FROM alternativa where id = ?;");
            ps.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    @Override
    public List pesquisar(String termo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List pesquisar(Object objeto) throws Exception {
        List<Alternativa> alternativas = new ArrayList();
        Pergunta pergunta = (Pergunta)objeto;
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, pergunta, questionario_id from pergunta where questionario_id=?");
            ps.setInt(1, pergunta.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Alternativa alternativa = new Alternativa();               
                alternativa.setId(rs.getInt("id"));
                alternativa.setResposta(rs.getString("resposta"));
                alternativa.setIsTrue(rs.getBoolean("isTrue"));
                alternativa.setPergunta(pergunta);
            }
            return alternativas;
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar alternativas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public void excluirDependente(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
