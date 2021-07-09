/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.usuarios;

import java.util.Objects;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ginger
 */
public class Correo {
    
    private String email;
    private final String remitente = "ventadevehiculosproyecto@hotmail.com";
    private static String clave = "12345678VV";
    
    public Correo(String email) {
        this.email = email;
    }       

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Correo other = (Correo) obj;
        if (!Objects.equals(this.email.toLowerCase(), other.email.toLowerCase())) {
            return false;
        }
        return true;
    }
    
     /**
     *
     * @param asunto
     * @param cuerpo
     * @return
     */
    public boolean enviarCorreo(String asunto, String cuerpo) {
        Properties props = System.getProperties();
        System.out.println("Enviando correo...");
        props.put("mail.smtp.host", "smtp.office365.com");  //El servidor SMTP de Hotmail
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro

        Session session = Session.getDefaultInstance(props);    
        MimeMessage message = new MimeMessage(session);       
        boolean isValid = false;
        try {           
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } 
        catch (AddressException e) { 
            System.out.println("You are in catch block -- Exception Occurred for: " + email); 
            return false;
        }       
        try {

            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));   
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.office365.com", remitente, clave);

            transport.sendMessage(message, message.getAllRecipients());

            transport.close();
        }
        catch (MessagingException me) {
            System.err.println(me.getMessage());   //Si se produce un error
        }
        return true;
    }
   
    
}
