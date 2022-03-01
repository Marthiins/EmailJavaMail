package enviando.email;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testeEmail() throws Exception {
		
		/*StringBuilder permite criar e manipular dados de Strings dinamicamente, ou seja, podem criar variáveis de String modificáveis.*/
		StringBuilder stringBuilderTextoEmail = new StringBuilder();

		stringBuilderTextoEmail.append("Olá, <br/><br/>");/*Html envio do email*/
		stringBuilderTextoEmail.append("Voce esta recebendo acesso ao curso de java. <br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso clique no botão abaixo. <br/><br/>");
		
		stringBuilderTextoEmail.append("<b>login:</b> sergio.marthiins@mail.com<br>");
		stringBuilderTextoEmail.append("<b>senha:</b>  <br><br/>");
	
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"https://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7; padding:14px 25px; text-align:center; text-decoration:none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border: 3px solid green; background-color:#99DA39;\">Acessar Portal do aluno</a><br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\">Ass.: Sérgio Marthiins</span>");
		
		/* Import Classe ObjetoEnviarEmail + objeto enviarEmail + constructor */
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("sergio.marthiins@gmail.com , sergio.marttthins@gmail.com", 
				"Sérgio Pessoa",
				"Testando email com java",
				stringBuilderTextoEmail.toString());

		enviaEmail.enviarEmailAnexo(true);

		/*
		 * Caso o email não esteja sendo enviado então coloque um tempo de espera mais
		 * observação so pode ser usado para testes
		 */
		Thread.sleep(2000);

	}
}
