package br.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.fiap.conexao.Conexao;
import br.fiap.entidade.BilheteUnico;

public class BilheteDAO {
	private Connection connection;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void inserir(BilheteUnico bilhete) {
		
		sql = "insert into java_bilhete(numero,cpf,saldo,valorPassagem) values(?, ?, ?,?)";
		connection = Conexao.conectar();
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bilhete.getNumero());
			ps.setString(2, bilhete.getCpfUsuario());
			ps.setDouble(3, bilhete.getSaldo());
			ps.setDouble(4, bilhete.getValorPassagem());
			ps.execute();
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao inserir aluno no banco\n" + e);
		}
	}

	public double consultarSaldo(String cpf) {
		double saldo = 0;
		connection = Conexao.conectar();
		sql = "select * from java_bilhete where cpf = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				saldo = rs.getDouble("saldo");
			}
		}
		catch(SQLException e) {
			System.out.println("ERRO AO CONSULTAR SALDO DO BILHETE \n" + e);
		}
		return saldo;
	}
	
	public void atualizarSaldo(String cpf,double saldo) {
		connection = Conexao.conectar();
		sql = "update java_bilhete set saldo = ? where cpf = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setDouble(1, saldo);
			ps.setString(2, cpf);
			ps.execute();		
			}
		catch(SQLException e) {
			System.out.println("erro ao atualizar o saldo\n" + e);
		}
		
	}
	
	public BilheteUnico consultar(String cpf) {
		BilheteUnico bilhete = null;
		connection = Conexao.conectar();
		sql = "update java_bilhete where cpf = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				int numero = rs.getInt("numero");
				double saldo = rs.getDouble("saldo");
				bilhete = new BilheteUnico(numero, cpf, saldo);
			}
		}
		catch (SQLException e) {
			System.out.println("erro ao consultar bilhete\n"+ e );
		}
		
		
		return bilhete;
	}
}
