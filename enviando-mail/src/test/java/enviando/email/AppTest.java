package enviando.email;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
   
    @Test
    public void testEmail() {
    	
    	/*Olhe as configurações SMTP do seu email*/
    	
   Properties properties = new Properties();
   properties.put("mail.smtp.auth", "true");/*Autorização*/
   properties.put("mail.smtp.starttls", "true");/*Autenticação*/
   properties.put("mail.smtp.host", "atmp.gmail.com");/*Servidor Gmail Google*/
   properties.put("mail.smtp.port", "465");/*Porta servidor*/
   properties.put("mail.smtp.socketFactory", "465");/*Expecifica a porta conectada pelo socket*/
   properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao smtp*/
    
    }
}
