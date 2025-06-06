/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;
import br.com.controle.Aluno;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ManterAlunoDAO extends DAO{
    public void inserir(Aluno a) throws Exception {
    try {
    abrirBanco();                      
    String query = "INSERT INTO aluno(codigo,nome,email,id_disciplina) "
            + "values(null,?,?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, a.getNome());
    pst.setString(2, a.getEmail());  
    pst.setInt(3, a.getIdDisciplina());
     System.out.println(a.getIdDisciplina());
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    }
    
    public void deletar(Aluno a) {
	try {
	abrirBanco();
	String query = "DELETE FROM aluno WHERE codigo = ?";
	pst = (PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, a.getCodigo());
	pst.execute();
	fecharBanco();
		
	} catch (Exception e) {
		System.out.println("Erro " + e.getMessage());
	}
    }
    public ArrayList<Aluno> PesquisarTudo() throws Exception {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();        
        try {
            abrirBanco();
            String query = "CALL listarAluno";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Aluno a;           
            while (tr.next()) {
                a = new Aluno();               
                a.setCodigo(tr.getInt("codigo"));
                a.setNome(tr.getString("nome"));
                a.setEmail(tr.getString("email"));
                a.setNomeDisciplina(tr.getString("disciplina"));
                alunos.add(a);                
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return alunos;
    }//listar
    
    public void PesquisarRegistro(Aluno a) throws Exception{
         try {
            abrirBanco();
            String query = "select * from aluno where codigo = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, a.getCodigo());
            ResultSet tr = pst.executeQuery();
         
            while (tr.next()) {                
                a.setEmail(tr.getString("email"));
                a.setNome(tr.getString("nome"));  
                a.setIdDisciplina(tr.getInt("id_disciplina"));
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    
     public void editarAluno(Aluno a) throws Exception{
        try {
            abrirBanco();
            String query = "UPDATE aluno set nome=?, email=?, id_disciplina=? where codigo=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, a.getNome());
            pst.setString(2, a.getEmail());
            pst.setInt(3, a.getIdDisciplina());
            pst.setInt(4, a.getCodigo());
            pst.executeUpdate();
            fecharBanco();
                    
        } catch (Exception e) {
              System.out.println("Erro " + e.getMessage());
        }
    }
}
