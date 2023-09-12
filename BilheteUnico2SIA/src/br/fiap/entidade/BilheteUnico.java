package br.fiap.entidade;

import java.util.Random;

public class BilheteUnico {
	private int numero;
	private String cpfUsuario;
	private double saldo;
	private static double valorPassagem = 4.40;
	
	public BilheteUnico(String cpfString) {
		this.numero = gerarNumero();
		this.cpfUsuario = cpfString;
	}
	
	
	
	public BilheteUnico(int numero, String cpfUsuario, double saldo) {
		super();
		this.numero = numero;
		this.cpfUsuario = cpfUsuario;
		this.saldo = saldo;
	}



	private int gerarNumero() {
		Random random = new Random();
		int numero = random.nextInt(9000) + 1000; // 0 --> 8999
		return numero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public static double getValorPassagem() {
		return valorPassagem;
	}

	public static void setValorPassagem(double valorPassagem) {
		BilheteUnico.valorPassagem = valorPassagem;
	}
	
	
}
