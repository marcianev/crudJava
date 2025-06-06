/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Professor;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


/**
 *
 * @author laboratorio
 */
public class ManterProfessor extends DAO {
    public void salvarProfessor(Professor p) throws Exception{
        try {
            abrirBanco();
            String sql = "INSERT INTO professor (codigo, nome, email, id_disciplina)"
                    + "values (null, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getEmail());
            pst.setInt(3, p.getIdDisciplina());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
              System.out.println("Erro " + e.getMessage());
        }
    }

    public void deletarProfessor(Professor p) throws Exception{
        try {
            abrirBanco();
            String sql = "delete from professor where codigo=?";
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, p.getCodigo());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
              System.out.println("Erro " + e.getMessage());
        }
        
    }
    
    public ArrayList<Professor> listarProfessor() throws Exception{
        ArrayList<Professor> prof = new ArrayList<Professor>();
        try {
            abrirBanco();
            String sql ="SELECT p.nome AS nome, p.codigo AS codigo, p.email as email, d.nome AS disciplina FROM professor AS p "
                    + "JOIN disciplina AS d ON p.id_disciplina = d.codigo";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet tr = pst.executeQuery();
            Professor p;
            while(tr.next()){
                p = new Professor();
                p.setCodigo(tr.getInt("codigo"));
                p.setNome(tr.getString("nome"));
                p.setEmail(tr.getString("email")); 
                p.setNomeDisciplina(tr.getString("disciplina"));
                prof.add(p);
            }
            fecharBanco();
        } catch (Exception e) {
              System.out.println("Erro " + e.getMessage());
        }
        return prof;
    }
    
    public void alterarProfessor(Professor p) throws Exception{
        try {
            abrirBanco();
            String sql = "UPDATE professor set nome=?, email=?, id_disciplina=? where codigo=?";
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getEmail());
            pst.setInt(3, p.getIdDisciplina());
            pst.setInt(4, p.getCodigo());
            pst.executeUpdate();
            fecharBanco();                    
        } catch (Exception e) {
              System.out.println("Erro " + e.getMessage());
        }
        
    }
    
    public void pesquisarRegistro(Professor p) throws Exception{
        try {
            abrirBanco();
            String sql = "select * from professor where codigo = ?";
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1,p.getCodigo());
            ResultSet tr = pst.executeQuery();
            while(tr.next()){
                p.setEmail(tr.getString("email"));
                p.setNome(tr.getString("nome"));
                p.setIdDisciplina(tr.getInt("id_disciplina"));
            }
            fecharBanco();
        } catch (Exception e) {
              System.out.println("Erro " + e.getMessage());
        }
    }
}