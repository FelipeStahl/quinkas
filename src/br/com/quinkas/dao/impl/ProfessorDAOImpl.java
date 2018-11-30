/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.entidade.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author erick
 */
public class ProfessorDAOImpl implements br.com.quinkas.dao.ProfessorDAO{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Integer insert(Professor professor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professor select(Integer id) throws Exception {
        Professor professor = new Professor();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome, email, senha from professor where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                professor.setId(rs.getInt("id"));
                professor.setSenha(rs.getString("senha"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
            } else {
                System.out.println("Não existe Professor neste ID");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar professor" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return professor;
    }
    
    @Override
    public Professor select(String email) throws Exception {
        Professor professor = new Professor();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome, email, senha from professor where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setSenha(rs.getString("senha"));
            } else {
                System.out.println("Não existe Professor com este Email");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar professor" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return professor;
    }

    @Override
    public void update(Professor professor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professor> list(String termo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professor> listAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
