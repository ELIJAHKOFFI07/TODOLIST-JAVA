package controleurObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static controleurObject.ControleurSignIn.showTooltip;
import static controleurObject.EmailSender.PreparationEmail;
import static controleurObject.Tools.addSlideAnimation;
import static controleurObject.Tools.animateLabel;

public class ControleurMotDePasse implements Initializable {
	@FXML
	private Label entete;
	@FXML
	private Label entete2;
	@FXML
	private Button effectuer;
	private Parent fxml;

	@FXML
	private PasswordField motDePass;
	@FXML
	private AnchorPane anchorpa;
	@FXML
	private TextField viewpassword;
	@FXML
	private CheckBox checkBox;
	@FXML
	private Hyperlink passwordforgot;

	private int tentative =0;
	 private Tooltip tooltip=new Tooltip();
	public ControleurMotDePasse() {
	}


	@FXML
	void check() {
		if (checkBox.isSelected()) {// le bouton checkbox est coche
			viewpassword.setText(motDePass.getText());
			viewpassword.setVisible(true);// le champs du textfield sera visible
			motDePass.setVisible(false);// le chaps du passwordfield sera invisibke
			return;
		}

		motDePass.setText(viewpassword.getText());
		motDePass.setVisible(true);// le passwordfied est visible
		viewpassword.setVisible(false);// le textfield est invisible

	}

	@FXML
	void renitialiserAction() throws SQLException {
		PreparationEmail("forgot");
		tooltip.setText("mot de passe envoyé sur Votre Email");

	}
	@FXML
	void acrioEffectuer() throws SQLException, IOException {


		String enteredPassword = checkBox.isSelected() ? viewpassword.getText() : motDePass.getText();//texte du Passwordfiel ou du Textfield


		Connection cn = BaseDeDonnes.connectbd();

		String getPasswordQuery = "SELECT password FROM admin WHERE password= ?";

		PreparedStatement stmt = cn.prepareStatement(getPasswordQuery);
		stmt.setString(1,enteredPassword);
		ResultSet resultSet = stmt.executeQuery();


		// parcours des lignes de la selection
		if (resultSet.next()) {

//			System.out.println("Bienvenue");
//			String sql = "UPDATE admin SET passApp=0 WHERE id=1";
//			stmt.executeUpdate(sql);
			stmt.close();
			cn.close();
			// Après avoir caché la fenêtre précédente
			anchorpa.getScene().getWindow().hide();

			Stage home = new Stage();
			Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/Acceuil.fxml"));
			VBox container = new VBox(fxml);
			addSlideAnimation(container,3000); // Ajoute l'animation de glissement

			Scene scene = new Scene(container);
			scene.setFill(Color.TRANSPARENT); // Rend le fond de la scène transparent (si vous utilisez StageStyle.TRANSPARENT)
			home.setScene(scene);
			home.show();

		}
		// Comparez le mot de passe actuel avec ce qui a été entré par l'utilisateur
	 else {
			cn.close();
			tentative++;
			System.out.println(tentative);
			// Les mots de passe ne correspondent pas, mettez à jour dans la base de données
			tooltip.setText("Mot de passe incorrect");
			// chaque 3 mauvaises tentatives un email est envoyé
			if (tentative==5){
				PreparationEmail("errorPassword");
				tentative=0;

			}

			if(checkBox.isSelected()){
			viewpassword.setTooltip(tooltip);
			showTooltip(viewpassword);
			System.out.println("Les mots de passe ne correspondent pas");}
			else {
				motDePass.setTooltip(tooltip);
				showTooltip(motDePass);
				System.out.println("Les mots de passe ne correspondent pas");

			}

		}
	}


		@Override
		public void initialize (URL arg0, ResourceBundle arg1){
			animateLabel(entete, entete.getText(), 4000);// effet de transition durant 3s
			animateLabel(entete2, entete2.getText(), 4000);// effet de transition pendant 5s


			motDePass.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					try {
						acrioEffectuer();

					} catch (SQLException | IOException e) {
						throw new RuntimeException(e);
					}
					//ajouter.requestFocus(); // Passer au champ ajouter

				}
			});
			viewpassword.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.ENTER) {
					try {
						acrioEffectuer();
					} catch (SQLException | IOException e) {
						throw new RuntimeException(e);
					}
					//ajouter.requestFocus(); // Passer au champ ajouter

				}
			});







		}

	}



