/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.dao.impl;

import br.com.quinkas.dao.ConnectionFactory;
import br.com.quinkas.dao.MateriaDAO;
import br.com.quinkas.dao.PerguntaDAO;
import br.com.quinkas.dao.ProfessorDAO;
import br.com.quinkas.dao.QuestionarioDAO;
import br.com.quinkas.dao.QuestionarioPerguntaDAO;
import br.com.quinkas.entidade.Materia;
import br.com.quinkas.entidade.Pergunta;
import br.com.quinkas.entidade.Professor;
import br.com.quinkas.entidade.Questionario;
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
public class QuestionarioDAOImpl implements br.com.quinkas.dao.QuestionarioDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Integer insert(Questionario questionario) throws Exception {
        /* Professor é obrigatorio no questionário. Se não souber que professor botar, ou se ainda não tiver implementação de professor,
                crie uma instancia vazia de Professor e seta o id=1, e bota como o professor do questionario. Vou dar um insert direto no banco
                de um professor com id=1 pra isso.
           Este método já registra todas as perguntas no questionário. Se você adicionar todas as perguntas ao questionário, e depois chamar este método,
                você não precisa registrar as perguntas no banco de dados depois.
         */
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("insert into questionario (nome, datacriacao, professor_id) "
                    + "values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, questionario.getNome());
            ps.setDate(2, new java.sql.Date(questionario.getDatacriacao().getTime()));
            ps.setInt(3, questionario.getProfessor().getId()); //IMPORTANTE TER UM PROFESSOR COM ID, POIS NO BANCO A FK ESTÁ COMO NOT NULL
            questionario.setId(ps.executeUpdate()); // Executa o insert e seta o id retornado no questionário
            if (questionario.getPerguntas().size() > 0) {
                // Insert das perguntas do questionário
                PerguntaDAO perguntaDao = new PerguntaDAOImpl();
                QuestionarioPerguntaDAO questPerguntaDao = new QuestionarioPerguntaDAOImpl();
                for (Pergunta pergunta : questionario.getPerguntas()) {
                    try {
                        if (pergunta.getId() == null) {
                            pergunta.setId(perguntaDao.insert(pergunta));
                        }
                        questPerguntaDao.insert(questionario, pergunta);
                    } catch (Exception e) {
                        System.out.println("Erro no insert da relação questionário e pergunta no metodo insert" + e);
                        continue;
                    }
                }
            }
            if (questionario.getMaterias().size() > 0) {
                // Insert do relacionamento do questionario com as materias na tabela 'questionario_pergunta'
                // AS MATÉRIAS NO QUESTIONÁRIO PRECISAM JÁ ESTAR COM ID
                ps = conn.prepareStatement("insert into questionario_materia (questionario_id, materia_id) values (?, ?)");
                for (Materia materia : questionario.getMaterias()) {
                    ps.setInt(1, questionario.getId());
                    ps.setInt(2, materia.getId());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Questionario não pôde ser criado" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return questionario.getId();
    }

    @Override
    public Questionario select(Integer id) throws Exception {
        // **Lembrete pra mim (Erick): Ajustar para incluir Alunos após entregar o trabalho do Wender**
        Questionario questionario = new Questionario();
        Professor professor = new Professor();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome, datacriacao, professor_id from questionario where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                PerguntaDAO perguntaDao = new PerguntaDAOImpl();
                MateriaDAO materiaDao = new MateriaDAOImpl();
                ProfessorDAO professorDao = new ProfessorDAOImpl();
                questionario.setId(rs.getInt("id"));
                questionario.setNome(rs.getString("nome"));
                questionario.setDatacriacao(rs.getDate("datacriacao"));
                professor = professorDao.select(rs.getInt("Professor_id"));
                questionario.setProfessor(professor);
                questionario.setPerguntas(perguntaDao.listPorQuestionario(questionario.getId()));
                questionario.setMaterias(materiaDao.listPorQuestionario(questionario.getId()));
            } else {
                System.out.println("Não existe Questionário neste ID");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar questionario" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return questionario;
    }

    @Override
    public void update(Questionario questionario) throws Exception {
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("update questionario set nome=?, datacriacao=?, Professor_id=? where id=?)");
            ps.setString(1, questionario.getNome());
            ps.setDate(2, new java.sql.Date(questionario.getDatacriacao().getTime()));
            ps.setInt(3, questionario.getProfessor().getId());
            ps.setInt(4, questionario.getId());
            ps.executeUpdate();
            this.updatePerguntas(questionario);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Questionário não pôde ser atualizado" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void updatePerguntas(Questionario questionario) throws Exception {
        QuestionarioPerguntaDAO questPerguntaDao = new QuestionarioPerguntaDAOImpl();
        for (Pergunta pergunta : questionario.getPerguntas()) {
            try {
                if (pergunta.getId() == null) {
                    PerguntaDAO perguntaDao = new PerguntaDAOImpl();
                    pergunta.setId(perguntaDao.insert(pergunta));
                    questPerguntaDao.insert(questionario, pergunta);
                    continue;
                }
                questPerguntaDao.insert(questionario, pergunta);
            } catch (Exception e) {
                System.out.println("Não foi possivel criar relação entre questionário e pergunta, ou relação já existia" + e);
                continue;
            }
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            QuestionarioPerguntaDAO questPerguntaDao = new QuestionarioPerguntaDAOImpl();
            questPerguntaDao.deleteAllQuestionario(id);
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("delete from questionario_materia where Questionario_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = conn.prepareStatement("delete from questionario where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar questionario" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public List<Questionario> listPorProfessor(Integer idProfessor) throws Exception {
        List<Questionario> questionarios = new ArrayList<Questionario>();
        QuestionarioDAO questionarioDao = new QuestionarioDAOImpl();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id from questionario where Professor_id=?");
            ps.setInt(1, idProfessor);
            rs = ps.executeQuery();
            while (rs.next()) {
                questionarios.add(questionarioDao.select(rs.getInt("id")));
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel resgatar lista de Questionarios" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return questionarios;
    }

    @Override
    public List<Questionario> listPorPergunta(Integer idPergunta) throws Exception {
        List<Questionario> questionarios = new ArrayList<Questionario>();
        try {
            QuestionarioPerguntaDAO questPerguntaDao = new QuestionarioPerguntaDAOImpl();
            questionarios = questPerguntaDao.selectQuestionarios(idPergunta);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar questionarios" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return questionarios;
    }

    @Override
    public List<Questionario> listPorMateria(Integer idMateria) throws Exception {
        List<Questionario> questionarios = new ArrayList<Questionario>();
        QuestionarioDAO questionarioDao = new QuestionarioDAOImpl();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select Questionario_id from questionario_materia where Materia_id=?");
            ps.setInt(1, idMateria);
            rs = ps.executeQuery();
            while (rs.next()) {
                questionarios.add(questionarioDao.select(rs.getInt("Questionario_id")));
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel resgatar lista de Questionarios" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return questionarios;
    }

    @Override
    public List<Questionario> listAll() throws Exception {
        List<Questionario> questionarios = new ArrayList<Questionario>();
        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement("select id, nome, datacriacao, professor_id from questionario");
            rs = ps.executeQuery();
            while (rs.next()) {
                Questionario questionario = new Questionario();
                Professor professor = new Professor();
                PerguntaDAO perguntaDao = new PerguntaDAOImpl();
                MateriaDAO materiaDao = new MateriaDAOImpl();
                questionario.setId(rs.getInt("id"));
                questionario.setNome(rs.getString("nome"));
                questionario.setDatacriacao(rs.getDate("datacriacao"));
                professor.setId(rs.getInt("Professor_id"));
                questionario.setProfessor(professor);
                questionario.setPerguntas(perguntaDao.listPorQuestionario(questionario.getId()));
                questionario.setMaterias(materiaDao.listPorQuestionario(questionario.getId()));
                questionarios.add(questionario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar questionarios" + e);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
        return questionarios;
    }
}
