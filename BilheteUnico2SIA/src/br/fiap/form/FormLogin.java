package br.fiap.form;
import static javax.swing.JOptionPane.*;

import br.fiap.DAO.usuarioDAO;

import static java.lang.Integer.parseInt;

public class FormLogin {
	
	public static void menu() {
		int opcao;
		String cpf;
		usuarioDAO dao = new usuarioDAO();
		
		do {
			opcao = parseInt(showInputDialog("1. Adinistrator\n2. Usuario\n3. sair"));
			switch(opcao) {
			case 1:
				FormAdmin.menu();
				break;
			case 2: 
				//pesquisar usuario no banco
				cpf = showInputDialog("CPF");
				if(dao.pesquisar(cpf)) {
				FormUsuario.menu(cpf);
				}else {
					showMessageDialog(null, "CPF NAO CADASTRADO");
				}
				
			}
		} while (opcao !=3);
	}

}
