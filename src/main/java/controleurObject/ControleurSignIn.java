package controleurObject;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.fxml.Initializable;

import static controleurObject.EmailSender.PreparationEmail;
import static controleurObject.Tools.addSlideAnimation;
import static controleurObject.Tools.animateLabel;
public class ControleurSignIn  implements Initializable  {


    @FXML
    private Label entete;
    @FXML
    private Label entete2;
    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelPrenom;
    @FXML
    private Button ajouter;
    private Parent fxml;

    @FXML
    private TextField email;

    @FXML
    private Hyperlink link;

    @FXML
    private PasswordField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private VBox signin;

    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField mdpcheck;


    // coloration en rouge d'un label
    void redColor(Label texte){
        texte.setStyle(texte.getStyle() + "-fx-text-fill: red;");
        texte.setStyle(texte.getStyle() + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 2, 0, 0, 1);");// ajouter une legere ombre
    }
    // coloration en rouge d'un label et tous les autres par defaut
    void initialisation(Label label1,Label label2,Label label3,Label label4){
        redColor(label1);
        String defautStyle="fx-font-size: 14px;"+ "-fx-text-fill: #B5B5B5;"+ "-fx-font-style: bold;";
        String PasswordStyle="fx-font-size: 10px;"+ "-fx-text-fill: #B5B5B5;"+ "-fx-font-style: bold;";
        label2.setStyle(defautStyle);
        label3.setStyle(defautStyle);
        if(label4.equals(labelPassword)){
            label4.setStyle(PasswordStyle);}
        else {
            label4.setStyle(defautStyle);
        }


    }
    // affichage du mot de passe
    @FXML
    void check(){
        if (checkBox.isSelected()){// le bouton checkbox est coche
            mdpcheck.setText(mdp.getText());
            mdpcheck.setVisible(true);// le champs du textfield sera visible
            mdp.setVisible(false);// le chaps du passwordfield sera invisibke
            return;
        }

        mdp.setText(mdpcheck.getText());
        mdp.setVisible(true);// le passwordfied est visible
        mdpcheck.setVisible(false);// le textfield est invisible

    }
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

            initialisation(labelEmail,labelPrenom,labelNom,labelPassword);
            email.setTooltip(falseAdress);
            showTooltip(email);}

        else if (mdp.getText().isEmpty()) {
            initialisation(labelPassword,labelEmail,labelNom,labelPrenom);
            tooltip.setText("Veuillez renseigner le champ mot de passe");
            mdp.setTooltip(tooltip);
            showTooltip(mdp);}
        else if (mdp.getText().length()< 8){
            initialisation(labelPassword,labelEmail,labelNom,labelPrenom);
            mdp.setTooltip(informal);
            showTooltip(mdp);
        }
        else if (checkBox.isSelected()&&mdpcheck.getText().isEmpty()) {
            initialisation(labelPassword,labelEmail,labelNom,labelPrenom);
            tooltip.setText("Veuillez renseigner le champ mot de passe");
            mdpcheck.setTooltip(tooltip);
            showTooltip(mdpcheck);}
        else if (checkBox.isSelected()&&mdpcheck.getText().length()< 8){
            initialisation(labelPassword,labelEmail,labelNom,labelPrenom);
            mdpcheck.setTooltip(informal);
            showTooltip(mdpcheck);
        }

        else {

            // ici sera mis tous les code relatif au controle sur saisir et
            //les enregistrement dans la base de  donné

            Connection cn=BaseDeDonnes.connectbd();
            Statement stmt = cn.createStatement ();



            String sql = "INSERT INTO admin (nom ,prenom ,email ,image,password,passApp) VALUES ( '"+nom.getText()+"', '"+prenom.getText()+"','"+email.getText()+"',"
                    + "'"+null+"','"+mdp.getText()+"','"+0+"')";
            stmt.executeUpdate(sql);
           // stmt.executeUpdate("commit");
            stmt.close ();
            cn.close ();
            // envoi de l'email de connexion

            signin.getScene().getWindow().hide();//pour supprimer l'ancien fenetre
            Stage home= new Stage();
            PreparationEmail("inscription");

            try {

                Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/MotDePasse.fxml"));
                VBox container = new VBox(fxml);
                addSlideAnimation(container,2500); // Ajoute l'animation de glissement

                Scene scene = new Scene(container);
                scene.setFill(Color.TRANSPARENT); // Rend le fond de la scène transparent (si vous utilisez StageStyle.TRANSPARENT)
                home.setScene(scene);
                home.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void showTooltip(Control control) {

        Tooltip tooltip = control.getTooltip(); // Récupère le Tooltip associé au contrôle
        tooltip.setAutoHide(true); // Définit le Tooltip pour se masquer automatiquement lorsque l'utilisateur clique à l'extérieur
        Bounds bounds = control.getBoundsInLocal(); // Récupère les coordonnées du contrôle dans son propre système de coordonnées local
        Bounds screenBounds = control.localToScreen(bounds); // Convertit les coordonnées du contrôle en coordonnées de l'écran
        double x = screenBounds.getMinX(); // Coordonnée x de l'angle supérieur gauche du contrôle par rapport à l'écran
        double y = screenBounds.getMaxY(); // Coordonnée y de l'angle inférieur gauche du contrôle par rapport à l'écran
        tooltip.show(control, x, y); // Affiche le Tooltip en utilisant les coordonnées spécifiées


    }


    // au demarrage de l'appli
    static void champsSuivant(Control a,Control b){
        a.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                b.requestFocus(); // Passer au champs suivant
            }
        });

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        animateLabel(entete,entete.getText(),7000);// effet de transition durant 3s
        animateLabel(entete2, entete2.getText(), 7000);// effet de transition pendant 5s

        champsSuivant(nom,prenom);
        champsSuivant(prenom,email);
        champsSuivant(email,mdp);

        mdp.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    ajouterAction();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //ajouter.requestFocus(); // Passer au champ ajouter

            }
        });
        mdpcheck.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    ajouterAction();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //ajouter.requestFocus(); // Passer au champ ajouter

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
