package controleurObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class ControleurSignIn22 implements Initializable  {

	
	@FXML
    private Button ajouter;
	  private Parent fxml;

    @FXML
    private TextField email;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNom;
    @FXML
    private Label labelPassword;

    @FXML
    private Label labelPrenom;



    @FXML
    private Hyperlink link;

    @FXML
    private TextField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private VBox signin;
    @FXML
    void ajouterAction() throws SQLException {
    	
    	
    	
    	 Tooltip tooltip=new Tooltip();
         Tooltip falseAdress=new Tooltip("Veuillez Entrer une adresse email valide");
         Tooltip informal=new Tooltip("Votre mot de passe doit contenir au moins 8 caracteres");

         if (nom.getText().isEmpty()) {

             initialisation(labelNom,labelEmail,labelPrenom,labelPassword);
             nom.setTooltip(tooltip);
             tooltip.setText("Veuillez renseigner le champ nom");
             showTooltip(nom);
         } else if (prenom.getText().isEmpty()) {

             initialisation(labelPrenom,labelEmail,labelNom,labelPassword);
             prenom.setTooltip(tooltip);
             tooltip.setText("Veuillez renseigner le champ prénom");
             showTooltip(prenom);

         } else if (email.getText().isEmpty()) {

             initialisation(labelEmail,labelPrenom,labelNom,labelPassword);
             email.setTooltip(tooltip);
             tooltip.setText("Veuillez renseigner le champ email");
             showTooltip(email);
         }

         else if (!email.getText().contains("@")) {

            // initialisation(labelEmail,labelPrenom,labelNom,labelPassword);
             email.setTooltip(falseAdress);
             showTooltip(email);}

         else if (mdp.getText().isEmpty()) {
             //initialisation(labelPassword,labelEmail,labelNom,labelPrenom);
             tooltip.setText("Veuillez renseigner le champ mot de passe");
             mdp.setTooltip(tooltip);
             showTooltip(mdp);}
         else if (mdp.getText().length()< 8){
             initialisation(labelPassword,labelEmail,labelNom,labelPrenom);
             mdp.setTooltip(informal);
             showTooltip(mdp);
         }
         else {
   
    	// ici sera mis tous les code relatif au controle sur saisir et
    	//les enregistrement dans la base de  donné
    	
    	Connection  cn=BaseDeDonnes.connectbd();
    	Statement stmt = cn.createStatement ();
    	
    	
    	
    	String sql = "INSERT INTO admin (nom ,prenom ,email ,image,password,passApp) VALUES ( '"+nom.getText()+"', '"+prenom.getText()+"','"+email.getText()+"',"
    			+ "'"+null+"','"+mdp.getText()+"','"+0+"')";
    	 stmt.executeUpdate(sql);
    	 stmt.executeUpdate("commit");
    	 stmt.close ();
		 cn.close ();
    	
    	 
    	signin.getScene().getWindow().hide();//pour supprimer l'ancien fenetre
    	Stage home= new Stage();
    	
    	try {
    		
			fxml= FXMLLoader.load(getClass().getResource("/interfaces/MotDePasse.fxml"));
			Scene scene=new Scene(fxml);
			home.setScene(scene);
			home.show();
			
		
			
		} catch (IOException e) {
			
			e.printStackTrace();}}


    }
    // coloration en rouge d'un label et tous les autres par defaut
    void initialisation(Label label1,Label label2,Label label3,Label label4){
        redColor(label1);
        String defautStyle="fx-font-size: 14px;"+ "-fx-text-fill: #B5B5B5;"+ "-fx-font-style: bold;";
        String PasswordStyle="fx-font-size: 10px;"+ "-fx-text-fill: #B5B5B5;"+ "-fx-font-style: bold;";
        if ( label2 != null) {
        label2.setStyle(defautStyle);
        label3.setStyle(defautStyle);
        if(label4.equals(labelPassword)){
            label4.setStyle(PasswordStyle);}
        else {
            label4.setStyle(defautStyle);
        }


    }}
    
    
    
    
    
    void redColor(Label texte){
    	if (texte != null) {
        texte.setStyle("-fx-text-fill: red;");
        texte.setStyle( "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");// ajouter une legere ombre
    }}
    
    private void showTooltip(Control control) {

        Tooltip tooltip = control.getTooltip(); // Récupère le Tooltip associé au contrôle
        tooltip.setAutoHide(true); // Définit le Tooltip pour se masquer automatiquement lorsque l'utilisateur clique à l'extérieur
        Bounds bounds = control.getBoundsInLocal(); // Récupère les coordonnées du contrôle dans son propre système de coordonnées local
        Bounds screenBounds = control.localToScreen(bounds); // Convertit les coordonnées du contrôle en coordonnées de l'écran
        double x = screenBounds.getMinX(); // Coordonnée x de l'angle supérieur gauche du contrôle par rapport à l'écran
        double y = screenBounds.getMaxY(); // Coordonnée y de l'angle inférieur gauche du contrôle par rapport à l'écran
        tooltip.show(control, x, y); // Affiche le Tooltip en utilisant les coordonnées spécifiées


    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    nom.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                prenom.requestFocus(); // Passer au champ de texte suivant (prenom)
            }
        });

        prenom.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                email.requestFocus(); // Passer au champ de texte suivant (email)
            }
        });

        email.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                mdp.requestFocus(); // Passer au champ de texte suivant (mdp)
            }
        });


        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z]*")) {
                return change; // Autorise la modification si le texte ne contient que des lettres
            }
            return null; // Bloque la modification si le texte contient des caractères autres que des lettres
        };

        TextFormatter<String> nomFormatter = new TextFormatter<>(filter);
        TextFormatter<String> PrenomFormatter = new TextFormatter<>(filter);
        nom.setTextFormatter(nomFormatter);// cette concrainte sera appliquée sur nom
        prenom.setTextFormatter(PrenomFormatter);// cette concrainte sera appliquée sur prenom




    }




		
	}


