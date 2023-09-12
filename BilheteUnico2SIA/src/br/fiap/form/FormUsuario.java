package br.fiap.form;

import static javax.swing.JOptionPane.*;

import br.fiap.DAO.BilheteDAO;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class FormUsuario {
	
	public static void menu(String cpf) {
		int opcao;
		
		do {
			opcao = parseInt(showInputDialog(gerarMenu()));
			switch(opcao) {
				case 1:
					carregarBilhete(cpf);
					break;
				case 2:
					passarCatraca(cpf);
					break;
				case 3:
					colsutarSaldo(cpf);
					break;
			}
		}while (opcao !=4);
	}

	private static void colsutarSaldo(String cpf) {
		BilheteDAO dao = new BilheteDAO();
		double saldo = dao.consultarSaldo(cpf);
		showMessageDialog(null, String.format("R$ %.2f", saldo));
		
	}

	private static void passarCatraca(String cpf) {
		// TODO Auto-generated method stub
		
	}

	public static String gerarMenu() {
		String aux = "Escolha uma operacao\n";
		aux+="1. Carregar bilhete\n";
		aux+="2. Passar na catraca\n";
		aux+="3. Consultar Saldo\n";
		aux+="4. Sair\n";
		return aux;
	}
	
	public static void carregarBilhete(String cpf) {
		BilheteDAO dao = new BilheteDAO();
		double valor = parseDouble(showInputDialog("valor de recarga"));
		double saldo = dao.consultarSaldo(cpf);
		saldo = saldo + valor;
		dao.atualizarSaldo(cpf,saldo);
	}
}
