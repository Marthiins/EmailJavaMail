package enviando.email;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testeEmail() throws Exception {

		/* Import Classe ObjetoEnviarEmail + objeto enviarEmail + constructor */
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("sergio.marthiins@gmail.com , sergio.marttthins@gmail.com", 
				"Sérgio Pessoa",
				"Testando email com java",
				"Esse texto é a descrição do meu e-mail.");

		enviaEmail.enviarEmail();

		/*
		 * Caso o email não esteja sendo enviado então coloque um tempo de espera mais
		 * observação so pode ser usado para testes
		 */
		Thread.sleep(2000);

	}
}
