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
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author erick
 */
public class ProfessorDAOImpl implements br.com.quinkas.dao.ProfessorDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public Integer inserir(Object objeto) throws Exception {
        try {
            Professor professor = (Professor)objeto;
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("insert into professor (nome, senha, email) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getSenha());
            ps.setString(3, professor.getEmail());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                return ultimoId;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new UnsupportedOperationException("Erro ao inserir professor. " + e.getMessage());
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public Integer alterar(Object objeto) throws Exception {
        try {
            Professor professor = (Professor)objeto;
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update professor set nome = ?, senha = ?, email = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getSenha());
            ps.setString(3, professor.getEmail());
            ps.setInt(4, professor.getId());
            int executeUpdate = ps.executeUpdate();
            if(executeUpdate != 0){
                return professor.getId();
            }else{
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
    public void delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Este método não está disponível na versão grátis.");
    }

    @Override
    public List pesquisar(String termo) throws Exception {
        throw new UnsupportedOperationException("Este método não está disponível na versão grátis.");
    }

    @Override
    public List pesquisar(Object objeto) throws Exception {
        throw new UnsupportedOperationException("Este método não está disponível na versão grátis.");
    }
    
    @Override
    public Boolean emailExiste(String email) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id from professor where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar professor. " + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return false;
    }

    @Override
    public Professor validarLogin(String email, String senha) throws Exception {
        try {
            Professor professor = new Professor();
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select * from professor where email=? AND senha = ?");
            ps.setString(1, email);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if (rs.next()) {
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setSenha(rs.getString("senha"));
                return professor;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao validar login. " + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return null;
    }
    
    @Override
    public void excluirDependente(Integer id) throws Exception {
        throw new UnsupportedOperationException("Este método não está disponível na versão grátis.");
    }

}
