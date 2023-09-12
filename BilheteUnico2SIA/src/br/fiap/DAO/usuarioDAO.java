package br.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.fiap.conexao.Conexao;
import br.fiap.entidade.BilheteUnico;
import br.fiap.entidade.TipoDeUsuario;
import br.fiap.entidade.Usuario;

public class usuarioDAO {
	private Connection connection;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public boolean pesquisar(String cpf) {
		boolean aux = false;
		connection = Conexao.conectar();
	    sql = "select * from java_usuario where cpf = ?";
	    
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery(); //somente para executar select 
			aux = rs.next();// next() retorna verdadeiro se tiver o registro pesquisado 
		}
		catch (SQLException e) {
			System.out.println("Erro ao pesquisar Usuario\n" + e);
		}
		return aux;
	}
	
	
	public void inserir(Usuario usuario) {
		BilheteDAO dao = new BilheteDAO();
		sql = "insert into java_usuario(nome,cpf,tipo) values(?, ?, ?)";
		connection = Conexao.conectar();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getTipo().name());
			ps.execute();
			dao.inserir(new BilheteUnico(usuario.getCpf()));
		}
		catch (SQLException e) {
			System.out.println("Erro ao inserir aluno no banco\n" + e);
		}
	}

	
	public  static void Usuario Usuario consultar(String cpf) {
		Usuario Usuario = null;
		connection = Conexao.conectar();
		sql = "select * from java_usuario where cpf = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome");
				TipoDeUsuario tipo ;
				String auxtipo= rs.getString("tipo");
				if (auxtipo.equalsIgnoreCase("normal")) {
					tipo = TipoDeUsuario.NORMAL;
				}else if(auxtipo.equalsIgnoreCase("estudante")){
					tipo = TipoDeUsuario.ESTUDANTE;
				}else {
					tipo = TipoDeUsuario.PROFESSOR;
				}
			}
			
		}
		catch(SQLException e) {
			System.out.println("Erro ao consultar usuario\n"+ e);
		}
		return Usuario;
	}
}
