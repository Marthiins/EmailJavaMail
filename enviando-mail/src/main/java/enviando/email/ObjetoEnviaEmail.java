package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String userName = "sergio.marttthins@gmail.com";
	private String senha = "";
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";

	/* Constructor com parametros da classe */
	public ObjetoEnviaEmail(String listaDestinatario, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatario;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;

	}

	/* Metodo com retorno */
	public void enviarEmail(boolean envioHtml) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");/* Autorização */
		properties.put("mail.smtp.starttls", "true");/* Autenticação */
		properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor Gmail Google */
		properties.put("mail.smtp.port", "465");/* Porta servidor */
		properties.put("mail.smtp.socketFactory.port", "465");/* Expecifica a porta conectada pelo socket */
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");/* Classe socket de conexão ao smtp */

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);/* Endereço array dos destinatarios */

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem esta enviando */
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);/* Assunto do email */

		if (envioHtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");/* SetContent passa o conteudo e o tipo */
		} else {
			message.setText(textoEmail);/* Corpo email */
		}
		Transport.send(message);/* Objeto de mensagem */
	}

	/* Metodo com retorno */
	public void enviarEmailAnexo(boolean envioHtml) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");/* Autorização */
		properties.put("mail.smtp.starttls", "true");/* Autenticação */
		properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor Gmail Google */
		properties.put("mail.smtp.port", "465");/* Porta servidor */
		properties.put("mail.smtp.socketFactory.port", "465");/* Expecifica a porta conectada pelo socket */
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");/* Classe socket de conexão ao smtp */

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);/* Endereço array dos destinatarios */

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem esta enviando */
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);/* Assunto do email */

		/* Parte 1 do e-mail que é o texto e a descrição */
		MimeBodyPart corpoEmail = new MimeBodyPart();

		if (envioHtml) {
			corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");/* SetContent passa o conteudo e o tipo */
		} else {
			corpoEmail.setText(textoEmail);/* Corpo email */
		}
		
		/*Parte 2 do e-mail que são os anexo em pdf*/
		MimeBodyPart anexoEmail = new MimeBodyPart();
		
		/*Onde é passado o simulador de PDF voce passa o seu arquivo gravado no banco de dados*/
		anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePDF(), "application/pdf")));
		anexoEmail.setFileName("anexoemail.pdf");
		
	   Multipart multipart = new MimeMultipart();/*Juntar a parte 1 com a parte 2*/
	   multipart.addBodyPart(corpoEmail);
	   multipart.addBodyPart(anexoEmail);
	   
	  message.setContent(multipart);/*Passando por mensagem*/
		
		Transport.send(message);/* Objeto de mensagem */
	}

	/**
	 * Esse método simula o PDF ou qualquer arquivo que possa ser enviado por
	 * anexono email Você pode pegar o arquivo no seu banco de dados base64, byte[]
	 * 
	 * Retorna um PDF em branco com o texto do paragrafo de exemplo
	 */
	private FileInputStream simuladorDePDF() throws Exception {
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));/* Escrevendo o meu documento dentro dele o file */
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com java Mail, esse texto é do PDF"));
		document.close();

		return new FileInputStream(file);
	}
}
