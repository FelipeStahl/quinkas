/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.dao.PerguntaDAO;
import br.com.quinkas.entidade.Alternativa;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Integer insert(Alternativa alternativa) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("insert into alternativa (alternativa, resposta, istrue, pergunta_id) "
                    + "values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alternativa.getAlternativa());
            ps.setString(2, alternativa.getResposta());
            ps.setBoolean(3, alternativa.getIsTrue());
            ps.setInt(4, alternativa.getPergunta().getId());
            alternativa.setId(ps.executeUpdate()); // Executa o insert e seta o id retornado na alternativa
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Alternativa não pôde ser criada" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return alternativa.getId();
    }

    @Override
    public Alternativa select(Integer id) throws Exception {
        Alternativa alternativa = new Alternativa();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, alternativa, resposta, istrue, pergunta_id from alternativa where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                PerguntaDAO perguntaDao = new PerguntaDAOImpl();
                alternativa.setId(rs.getInt("id"));
                alternativa.setAlternativa(rs.getInt("alternativa"));
                alternativa.setResposta(rs.getString("resposta"));
                alternativa.setIsTrue(rs.getBoolean("isTrue"));
                alternativa.setPergunta(perguntaDao.select(rs.getInt("Pergunta_id")));
            } else {
                System.out.println("Não existe Alternativa neste ID");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar alternativa" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return alternativa;
    }

    @Override
    public void update(Alternativa alternativa) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update alternativa set alternativa=?, resposta=?, istrue=?, Pergunda_id=? where id=?)");
            ps.setInt(1, alternativa.getAlternativa());
            ps.setString(2, alternativa.getResposta());
            ps.setBoolean(3, alternativa.getIsTrue());
            ps.setInt(4, alternativa.getPergunta().getId());
            ps.setInt(5, alternativa.getId());
            ps.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Matéria não pôde ser atualizada" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("delete from alternativa where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar alternativa" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public List<Alternativa> list(String termo) throws Exception {
        List<Alternativa> alternativas = new ArrayList<Alternativa>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, alternativa, resposta, isTrue, Pergunta_id from alternativa where resposta like ?");
            ps.setString(1, "%" + termo + "%");
            rs = ps.executeQuery();
            PerguntaDAO perguntaDao = new PerguntaDAOImpl();
            while (rs.next()) {
                Alternativa alternativa = new Alternativa();
                alternativa.setId(rs.getInt("id"));
                alternativa.setAlternativa(rs.getInt("alternativa"));
                alternativa.setResposta(rs.getString("resposta"));
                alternativa.setIsTrue(rs.getBoolean("isTrue"));
                alternativa.setPergunta(perguntaDao.select(rs.getInt("Pergunta_id")));
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar alternativas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return alternativas;
    }

    @Override
    public List<Alternativa> listPorPergunta(Integer idPergunta) throws Exception {
        List<Alternativa> alternativas = new ArrayList<Alternativa>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, alternativa, resposta, isTrue, Pergunta_id from alternativa where Pergunta_id=? order by alternativa");
            ps.setInt(1, idPergunta);
            rs = ps.executeQuery();
            PerguntaDAO perguntaDao = new PerguntaDAOImpl();
            while (rs.next()) {
                Alternativa alternativa = new Alternativa();
                alternativa.setId(rs.getInt("id"));
                alternativa.setAlternativa(rs.getInt("alternativa"));
                alternativa.setResposta(rs.getString("resposta"));
                alternativa.setIsTrue(rs.getBoolean("isTrue"));
                alternativa.setPergunta(perguntaDao.select(rs.getInt("Pergunta_id")));
                alternativas.add(alternativa);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar alternativas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return alternativas;
    }

    @Override
    public List<Alternativa> listAll() throws Exception {
        List<Alternativa> alternativas = new ArrayList<Alternativa>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, alternativa, resposta, isTrue, Pergunta_id from alternativa");
            rs = ps.executeQuery();
            PerguntaDAO perguntaDao = new PerguntaDAOImpl();
            while (rs.next()) {
                Alternativa alternativa = new Alternativa();
                alternativa.setId(rs.getInt("id"));
                alternativa.setAlternativa(rs.getInt("alternativa"));
                alternativa.setResposta(rs.getString("resposta"));
                alternativa.setIsTrue(rs.getBoolean("isTrue"));
                alternativa.setPergunta(perguntaDao.select(rs.getInt("Pergunta_id")));
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar alternativas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return alternativas;
    }
    
}
