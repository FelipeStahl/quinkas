/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.entidade.Pergunta;
import br.com.quinkas.entidade.Professor;
import br.com.quinkas.entidade.Questionario;
import br.com.quinkas.manter.ManterProfessor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erick
 */
public class QuestionarioDAOImpl implements br.com.quinkas.dao.QuestionarioDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Integer inserir(Object objeto) throws Exception {
        try {
            Questionario questionario = (Questionario) objeto;
            if (questionario.getId() == null) {
                conn = ConnectionFactory.getConnection();
                ps = conn.prepareStatement("insert into questionario (nome, datacriacao, professor_id) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, questionario.getNome());
                ps.setDate(2, new Date(questionario.getDatacriacao().getTime()));
                ps.setInt(3, questionario.getProfessor().getId());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int ultimoId = rs.getInt(1);
                    return ultimoId;
                } else {
                    return null;
                }
            } else {
                return alterar(questionario);
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
            Questionario questionario = (Questionario) objeto;
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update questionario set nome = ?, datacriacao = ?, professor_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, questionario.getNome());
            ps.setDate(2, new Date(questionario.getDatacriacao().getTime()));
            ps.setInt(3, questionario.getProfessor().getId());
            ps.setInt(4, questionario.getId());
            int executeUpdate = ps.executeUpdate();
            if (executeUpdate != 0) {
                return questionario.getId();
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
        Questionario questionario;
        Professor professor;
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome, datacriacao, professor_id from questionario where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                questionario = new Questionario();
                PerguntaDAOImpl perguntaDao = new PerguntaDAOImpl();
                questionario.setId(rs.getInt("id"));
                questionario.setNome(rs.getString("nome"));
                questionario.setDatacriacao(rs.getDate("datacriacao"));
                if (ManterProfessor.getProfessor() != null) {
                    professor = ManterProfessor.getProfessor();
                } else {
                    ProfessorDAOImpl professorDao = new ProfessorDAOImpl();
                    professor = (Professor) professorDao.select(rs.getInt("Professor_id"));
                }
                questionario.setProfessor(professor);
                questionario.setPerguntas(perguntaDao.pesquisar(questionario.getId()));
                return questionario;
            } else {
                System.out.println("Não existe Questionário neste ID");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar questionario" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            excluirDependente(id);
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("DELETE FROM questionario where id = ?;");
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
        List<Questionario> questionarios = new ArrayList();
        Professor professor = (Professor)objeto;
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome, datacriacao, professor_id from questionario where professor_id=?");
            ps.setInt(1, professor.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Questionario questionario = new Questionario();               
                questionario.setId(rs.getInt("id"));
                questionario.setNome(rs.getString("nome"));
                questionario.setDatacriacao(rs.getDate("datacriacao"));
                questionario.setProfessor(professor);
                PerguntaDAOImpl perguntaDao = new PerguntaDAOImpl();
                questionario.setPerguntas(perguntaDao.pesquisar(questionario));
                questionarios.add(questionario);
            }
            return questionarios;
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar questionario" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public void excluirDependente(Integer id) throws Exception {
        try {
            PerguntaDAOImpl perguntaDao = new PerguntaDAOImpl();
            List<Pergunta> perguntas = perguntaDao.pesquisar(id);
            for (Pergunta pergunta : perguntas) {
                perguntaDao.delete(pergunta.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
