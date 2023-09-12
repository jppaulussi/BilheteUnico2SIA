package br.fiap.form;
import static javax.swing.JOptionPane.*;

import br.fiap.DAO.BilheteDAO;
import br.fiap.DAO.usuarioDAO;
import br.fiap.entidade.BilheteUnico;
import br.fiap.entidade.TipoDeUsuario;
import br.fiap.entidade.Usuario;

import static java.lang.Integer.parseInt;

public class FormAdmin {
	
	public static void menu () {
		int opcao;
		do {
			opcao = parseInt(showInputDialog(gerarMenu()));
			switch (opcao) {
			case 1:
				emitirBilhete();
				break;
			case 2:
				imprimirBilhete();
				break;
			case 3:
				consultarBilhete();
				break;
			}
		}while(opcao !=4);
	}

	private static void consultarBilhete() {
		String cpf = showInputDialog("CPF");
		usuarioDAO UsuarioDAO = new usuarioDAO();
		BilheteDAO bilheteDAO = new BilheteDAO();
		Usuario Usuario = usuarioDAO.consultar(cpf);
		BilheteUnico bilhete = BilheteDAO.consultar(cpf);
		String aux = "";
		aux += "CPF:"+ Usuario.getCpf()+"\n";
		aux += "Nome"+Usuario.getNome()+"\n";
		aux += "Saldo: R$"+ String.format("%.2f", bilhete.getSaldo())+"\n";
		aux += "Tipo de Tarifa" + Usuario.getTipo().name();
		showMessageDialog(getRootFrame(), aux);
		
	}

	private static void imprimirBilhete() {
		// TODO Auto-generated method stub
		
	}

	public static String gerarMenu() {
		String aux = "Escolha uma Operacao\n";
		aux +="1.Emitir bilhete\n";
		aux +="2.Imprimir bilhete\n";
		aux +="3.Consultar bulhete\n";
		aux +="4.Sair";
		return aux;
	}
	
	
 
	public static void emitirBilhete() {
		String cpf, nome;
		TipoDeUsuario tipo = null;
		int tipoUsuario;
		usuarioDAO dao = new usuarioDAO();
		
		cpf = showInputDialog("CPF");
		if(dao.pesquisar(cpf)) {
			showMessageDialog(null, "CPF ja tem bilhete cadastro");
		}else {
			nome = showInputDialog("Nome");
			tipoUsuario = parseInt(showInputDialog("1. Normal\n2 Estudante\n3. Professor"));
			switch(tipoUsuario) {
			case 1:
				tipo = TipoDeUsuario.NORMAL;
				break;
			case 2:
				tipo = TipoDeUsuario.ESTUDANTE;
				break;
			case 3: 
				tipo = TipoDeUsuario.PROFESSOR;
			}
			dao.inserir(new Usuario(nome,cpf,tipo));
		}
		
	}
}
