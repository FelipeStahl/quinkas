/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.dao.MateriaDAO;
import br.com.quinkas.entidade.Materia;
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
public class MateriaDAOImpl implements br.com.quinkas.dao.MateriaDAO {
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Integer insert(Materia materia) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("insert into materia (nome) values (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNome());
            materia.setId(ps.executeUpdate()); // Executa o insert e seta o id retornado na materia
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Matéria não pôde ser criada" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return materia.getId();
    }

    @Override
    public Materia select(Integer id) throws Exception {
        // Este método NÃO retorna a matéria com os questionários que à contém.
        // Para isso, use o método listPorMateria do QuestionarioDAOImpl
        Materia materia = new Materia();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome from materia where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
            } else {
                System.out.println("Não existe Matéria neste ID");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar matéria" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return materia;
    }

    @Override
    public void update(Materia materia) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update materia set nome=? where id=?)");
            ps.setString(1, materia.getNome());
            ps.setInt(2, materia.getId());
            ps.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Matéria não pôde ser atualizada" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

//    @Override
//    public void delete(Integer id) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public List<Materia> list(String termo) throws Exception {
        List<Materia> materias = new ArrayList<Materia>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome from materia where nome like ?");
            ps.setString(1, "%" + termo + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
                materias.add(materia);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar materias" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return materias;
    }

    @Override
    public List<Materia> listPorQuestionario(Integer idQuestionario) throws Exception {
        List<Materia> materias = new ArrayList<Materia>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select Materia_id from questionario_materia where Questionario_id=?");
            ps.setInt(1, idQuestionario);
            rs = ps.executeQuery();
            MateriaDAO materiaDao = new MateriaDAOImpl();
            while (rs.next()) {
                Materia materia = materiaDao.select(rs.getInt("Materia_id"));
                materias.add(materia);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar materias" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return materias;
    }
    
    @Override
    public List<Materia> listAll() throws Exception {
        List<Materia> materias = new ArrayList<Materia>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome from materia");
            rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
                materias.add(materia);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar materias" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return materias;
    }
}
