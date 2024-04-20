package controleurObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void main(String[] args) {
        String recipientEmail = "piroukoffi@gmail.com"; // adresse e-mail du destinataire
        String emailSubject = "Sujet de l'e-mail";
        String emailBody = "Contenu de l'e-mail";

        sendEmail(recipientEmail, emailSubject, emailBody);
    }
    public static void PreparationEmail(String message) throws SQLException {
        // envoi de l'email au client
        Connection cn = BaseDeDonnes.connectbd();// connexion a la base de donnée
        Statement stmt2= cn.createStatement();
        Statement stmt3= cn.createStatement();
        Statement stmt4= cn.createStatement();



        String nomDestinataire = "",prenomDestinataire = "",destinataireEmail="",BodyLetter="";
        try (ResultSet resultSetNom = stmt2.executeQuery("SELECT nom FROM admin WHERE passApp=0");
             ResultSet resultSetPrenom = stmt3.executeQuery("SELECT prenom FROM admin WHERE passApp=0");
             ResultSet resultSetEmail = stmt4.executeQuery("SELECT email FROM admin WHERE passApp=0")) {

            if (resultSetNom.next()) {
                nomDestinataire = resultSetNom.getString("nom");// recuperation de la colonne nom
            }
            if (resultSetPrenom.next()) {
                prenomDestinataire = resultSetPrenom.getString("prenom");// recuperation de la colonne prenom
            }
            if (resultSetEmail.next()) {
                destinataireEmail = resultSetEmail.getString("email");// recuperation de la colonne email
            }
            String ObjetEmail=" Todolist Ycdit  ";
            switch (message){

                // Pour mot de passe oublié
                case"forgot":
                    // select de la colonne password :
                    Statement stmt = cn.createStatement();
                    String getPasswordQuery = "SELECT password FROM admin WHERE passApp=0";
                    ResultSet resultSet = stmt.executeQuery(getPasswordQuery);
                    String currentPassword = "";

                    // parcours des lignes de la selection
                    if (resultSet.next()) {
                        currentPassword = resultSet.getString("password");
                    }

                    BodyLetter="M/Mme"+nomDestinataire+" "+prenomDestinataire+"\n votre mot de passe est : "+currentPassword;
                    System.out.println(" mot de passe renitialise");
                    break;


                case "inscription":
                    String a=" M/Mme "+nomDestinataire+" "+prenomDestinataire+"\n Votre inscription est reussi ";

                    BodyLetter="Bienvenue M/Mme "+nomDestinataire+" "+prenomDestinataire+"\n Notre todolist vous offre plein de fonctionnalites \n Equipe :\n Diahou yapo : Developpeur et administrateur de base de donées\n Romaric koffi:Ingenieur en IA,\n Daniel Koffi: Machinelearniste\n"+a;

                    break;
                case "errorPassword":
                    BodyLetter="Vous avez saisi plusieurs fois des mots de passes erronés veuillez cliquer sur mot de passe oublié pour rénitialiser le mot de passe";
                    break;
            }
            sendEmail(destinataireEmail,ObjetEmail , BodyLetter);// envoie de l'email


            // Reste du code pour l'envoi d'email
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cn.close();
    }

    public static void sendEmail(String recipientEmail, String subject, String body) {
        final String senderEmail = "cd5031843@gmail.com"; // Remplacez par votre propre adresse e-mail
        final String senderPassword = "t l p h d h n f i y p u o q d h"; //votre mot de passe d'application généré sous l'onglet sécurité de google

        // Configuration des propriétés pour la connexion au serveur SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Remplacez par l'adresse du serveur SMTP
        properties.put("mail.smtp.port", "587"); // Port SMTP

        // Création d'une session de messagerie avec authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Envoi du message
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }
}

