/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;
import br.com.controle.Disciplina;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author laboratorio
 */
public class ManterDisciplina extends DAO{    
    public void inserir(Disciplina d) throws Exception {
    try {
    abrirBanco();                      
    String query = "INSERT INTO disciplina (codigo,nome, carga_hora) "
            + "values(null,?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, d.getNome());
    pst.setInt(2, d.getCarga());    
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    }
    
    public void deletar(Disciplina d) {
	try {
	abrirBanco();
	String query = "DELETE FROM disciplina WHERE codigo = ?";
	pst = (PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, d.getCodigo());
	pst.execute();
	fecharBanco();
		
	} catch (Exception e) {
		System.out.println("Erro " + e.getMessage());
	}
    }
    public ArrayList<Disciplina> PesquisarTudo() throws Exception {
        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
        try {
            abrirBanco();
            String query = "call listarGeral";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Disciplina d;
            while (tr.next()) {
                d = new Disciplina();
                d.setCodigo(tr.getInt("codigo"));
                d.setNome(tr.getString("disciplina")); 
               d.setProfessor(tr.getString("professor"));
               d.setAluno(tr.getString("aluno"));
                disciplinas.add(d);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return disciplinas;
    }//listar
    
    public void pesquisarRegistro(Disciplina d) throws Exception{
         try {
            abrirBanco();
            String query = "select * FROM disciplina where codigo = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, d.getCodigo());
            ResultSet tr = pst.executeQuery();
           
            while (tr.next()) {                
                d.setCarga(tr.getInt("carga_hora"));
                d.setNome(tr.getString("nome"));                            
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    
     public void editarDisciplina(Disciplina d) throws Exception{
        try {
            abrirBanco();
            String query = "UPDATE disciplina set nome=?, carga_hora=? where codigo=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, d.getNome());
            pst.setInt(2, d.getCarga());
            pst.setInt(3, d.getCodigo());
            pst.executeUpdate();
            fecharBanco();
                    
        } catch (Exception e) {
              System.out.println("Erro " + e.getMessage());
        }
    }

}
