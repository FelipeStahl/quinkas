/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.AlternativaDAO;
import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.dao.MateriaDAO;
import br.com.quinkas.dao.QuestionarioPerguntaDAO;
import br.com.quinkas.entidade.Alternativa;
import br.com.quinkas.entidade.Pergunta;
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
public class PerguntaDAOImpl implements br.com.quinkas.dao.PerguntaDAO {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Integer insert(Pergunta pergunta) throws Exception {
        /* Quando chamar este método, garanta que você já tenha em mãos o questionário que você irá relacionar à essa pergunta, se não o estiver chamando
         *      pelo método insert do QuestionarioDAOImpl.
         * Este método irá retornar o ID da pergunta gerada, e você cria o relacionamento na tabela 'questionario_pergunta' com o id dos dois.
         * Este método NÃO cria o relacionamento do questionário com a pergunta,
         *      apenas cria a pergunta, as alternativas, e relaciona as alternativas com a pergunta.
         */
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("insert into pergunta (pergunta, escopo, materia_id, imagepath) "
                    + "values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, pergunta.getPergunta());
            ps.setString(2, pergunta.getEscopo());
            ps.setInt(3, pergunta.getMateria().getId());
            ps.setString(4, pergunta.getImagePath());
            pergunta.setId(ps.executeUpdate()); // Executa o insert e seta o id retornado na pergunta
            if (pergunta.getAlternativas().size() > 0) {
                // Insert das alternativas da pergunta
                AlternativaDAO alternativaDAO = new AlternativaDAOImpl();
                for (Alternativa alternativa : pergunta.getAlternativas()) {
                    alternativa.setPergunta(pergunta);
                    alternativa.setId(alternativaDAO.insert(alternativa)); // Insere alternativa no banco e seta id criado na instancia da alternativa
                }
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Pergunta não pôde ser criada" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return pergunta.getId();
    }

    @Override
    public Pergunta select(Integer id) throws Exception {
        // Este método NÃO retorna a pergunta com os os questionários em que ela pertence.
        // Se você quer a lista de questionarios com esta pergunta, use o método listPorPergunta do QuestionarioDAOImpl
        Pergunta pergunta = new Pergunta();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, pergunta, escopo, materia_id, imagepath from pergunta where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                MateriaDAO materiaDao = new MateriaDAOImpl();
                pergunta.setId(rs.getInt("id"));
                pergunta.setPergunta(rs.getString("pergunta"));
                pergunta.setEscopo(rs.getString("escopo"));
                pergunta.setImagePath(rs.getString("imagepath"));
                pergunta.setMateria(materiaDao.select(rs.getInt("Materia_id")));
                AlternativaDAO alternativaDao = new AlternativaDAOImpl();
                pergunta.setAlternativas(alternativaDao.listPorPergunta(pergunta.getId()));
            } else {
                System.out.println("Não existe Pergunta neste ID");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar pergunta" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return pergunta;
    }

    @Override
    public void update(Pergunta pergunta) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update pergunta set pergunta=?, escopo?, materia_id=?, imagepath=? where id=?)");
            ps.setString(1, pergunta.getPergunta());
            ps.setString(2, pergunta.getEscopo());
            ps.setInt(3, pergunta.getMateria().getId());
            ps.setString(4, pergunta.getImagePath());
            ps.setInt(5, pergunta.getId());
            ps.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Pergunta não pôde ser atualizada" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            QuestionarioPerguntaDAO questPerguntaDao = new QuestionarioPerguntaDAOImpl();
            questPerguntaDao.deleteAllPergunta(id);
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("delete from pergunta where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar pergunta" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public List<Pergunta> list(String termo) throws Exception {
        List<Pergunta> perguntas = new ArrayList<Pergunta>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, pergunta, escopo, materia_id, imagepath from pergunta where pergunta like ? or escopo like ?");
            ps.setString(1, "%" + termo + "%");
            ps.setString(2, "%" + termo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Pergunta pergunta = new Pergunta();
                MateriaDAO materiaDao = new MateriaDAOImpl();
                pergunta.setId(rs.getInt("id"));
                pergunta.setPergunta(rs.getString("pergunta"));
                pergunta.setEscopo(rs.getString("escopo"));
                pergunta.setImagePath(rs.getString("imagepath"));
                pergunta.setMateria(materiaDao.select(rs.getInt("Materia_id")));
                AlternativaDAO alternativaDao = new AlternativaDAOImpl();
                pergunta.setAlternativas(alternativaDao.listPorPergunta(pergunta.getId()));
                perguntas.add(pergunta);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar perguntas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return perguntas;
    }

    @Override
    public List<Pergunta> listPorMateria(Integer idMateria) throws Exception {
        List<Pergunta> perguntas = new ArrayList<Pergunta>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, pergunta, escopo, Materia_id, imagepath from pergunta where Materia_id=?");
            ps.setInt(1, idMateria);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pergunta pergunta = new Pergunta();
                MateriaDAO materiaDao = new MateriaDAOImpl();
                pergunta.setId(rs.getInt("id"));
                pergunta.setPergunta(rs.getString("pergunta"));
                pergunta.setEscopo(rs.getString("escopo"));
                pergunta.setImagePath(rs.getString("imagepath"));
                pergunta.setMateria(materiaDao.select(rs.getInt("Materia_id")));
                AlternativaDAO alternativaDao = new AlternativaDAOImpl();
                pergunta.setAlternativas(alternativaDao.listPorPergunta(pergunta.getId()));
                perguntas.add(pergunta);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar perguntas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return perguntas;
    }

    @Override
    public List<Pergunta> listPorQuestionario(Integer idQuestionario) throws Exception {
        List<Pergunta> perguntas = new ArrayList<Pergunta>();
        try {
            QuestionarioPerguntaDAO questPerguntaDao = new QuestionarioPerguntaDAOImpl();
            perguntas = questPerguntaDao.selectPerguntas(idQuestionario);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar perguntas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return perguntas;
    }

    @Override
    public List<Pergunta> listAll() throws Exception {
        List<Pergunta> perguntas = new ArrayList<Pergunta>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, pergunta, escopo, Materia_id, imagepath from pergunta");
            rs = ps.executeQuery();
            while (rs.next()) {
                Pergunta pergunta = new Pergunta();
                MateriaDAO materiaDao = new MateriaDAOImpl();
                pergunta.setId(rs.getInt("id"));
                pergunta.setPergunta(rs.getString("pergunta"));
                pergunta.setEscopo(rs.getString("escopo"));
                pergunta.setImagePath("imagepath");
                pergunta.setMateria(materiaDao.select(rs.getInt("Materia_id")));
                AlternativaDAO alternativaDao = new AlternativaDAOImpl();
                pergunta.setAlternativas(alternativaDao.listPorPergunta(pergunta.getId()));
                perguntas.add(pergunta);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar perguntas" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return perguntas;
    }
    
}
