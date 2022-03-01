package enviando.email;

import java.net.InterfaceAddress;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private String userName = "";
	private String senha = "";

	@Test
	public void testEmail() {
		try {

			/* Olhe as configurações SMTP do seu email */

			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");/* Autorização */
			properties.put("mail.smtp.starttls", "true");/* Autenticação */
			properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor Gmail Google */
			properties.put("mail.smtp.port", "465");/* Porta servidor */
			properties.put("mail.smtp.socketFactory.port" ,"465");/* Expecifica a porta conectada pelo socket */
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");/* Classe socket de conexão ao smtp */

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, senha);
				}
			});

			Address[] toUser = InternetAddress.parse("sergio.marthiins@gmail.com , sergio.martthins@gmail.com");

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));/* Quem esta enviando */
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Chegou o email enviado com java");/*Assunto do email*/
		    message.setText("Ola programador você acaba de receber um email com java");/*Corpo email*/
		
		Transport.send(message);/*Objeto de mensagem*/
		
		} catch (Exception e) {
			e.printStackTrace();/* Mostrar no console */
		}

	}
}
