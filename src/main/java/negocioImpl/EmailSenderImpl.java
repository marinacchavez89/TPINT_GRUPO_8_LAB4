package negocioImpl;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import negocio.EmailSenderNegocio;

import java.util.Properties;

public class EmailSenderImpl implements EmailSenderNegocio {
	  public void enviarNuevaPassword(String destinatario, String nuevaPass) {
	        final String remitente = "laboivgrupo8@gmail.com";
	        final String clave = "juxeykhycntejrux";

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session sesion = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(remitente, clave);
	            }
	        });

	        try {
	            Message mensaje = new MimeMessage(sesion);
	            mensaje.setFrom(new InternetAddress(remitente));
	            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
	            mensaje.setSubject("Tu nueva contraseña");
	            mensaje.setText("Hola, tu nueva contraseña es: " + nuevaPass + " Por tu seguridad, cambiala cuanto antes.");

	            Transport.send(mensaje);
	            System.out.println("Correo enviado correctamente");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
}
